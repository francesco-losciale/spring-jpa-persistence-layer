package com.persistence.base;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

import com.persistence.annotation.SoftDeleteActive;
import com.persistence.exception.OperationException;

public class CrudRepository<EntityObjectType extends BaseEntity> extends BasePersistenceProvider {

	@Autowired
	private AuditorAware<String> persistenceAuditorAware;

	
	public EntityObjectType get(Class<EntityObjectType> resultClass, Object id) throws OperationException {
		EntityManager em = getEntityManager();
		EntityObjectType resultEntity = null;
		try {		
			resultEntity = em.getReference(resultClass, id);
		} catch (Exception e) {
			throw new OperationException(e);
		}
		return resultEntity;
	}
	
	
	public EntityObjectType insert(EntityObjectType entity) throws OperationException {
		EntityManager em = getEntityManager();
		try {		
			em.persist(entity);
		} catch (Exception e) {
			throw new OperationException(e);
		} finally {
			em.flush();
		}
		return entity;
	}

	
	public EntityObjectType update(EntityObjectType entity) throws OperationException {
		EntityManager em = getEntityManager();
		try {		
			em.merge(entity);
		} catch (Exception e) {
			throw new OperationException(e);
		} finally {
			em.flush();
		}
		return entity;
	}
	
	public void delete(EntityObjectType entity) throws OperationException {
		
		EntityManager em = getEntityManager();
		try {
			boolean isSoftDeleteActive = entity.getClass().isAnnotationPresent(SoftDeleteActive.class); 
			if (isSoftDeleteActive) {
				
				// soft delete on parent object
				entity.setDateDelete(Calendar.getInstance().getTime());
				entity.setUserDelete(persistenceAuditorAware.getCurrentAuditor());
				em.merge(entity);

				//look for child objects on which to apply soft delete
				for (Method m : entity.getClass().getMethods()) {
					boolean isAtLeastOneChildPresent = m.isAnnotationPresent(OneToMany.class) || 
															m.isAnnotationPresent(ManyToMany.class);
					if (isAtLeastOneChildPresent) {
						Class<?> c = m.getReturnType();
						if (Collection.class.isAssignableFrom(c)) {
							@SuppressWarnings("unchecked")
							Collection<BaseEntity> list = (Collection<BaseEntity>)m.invoke(entity);
							for (BaseEntity baseEntity : list) {
								if (baseEntity.getDateDelete() == null) {
									baseEntity.setDateDelete(Calendar.getInstance().getTime());
									baseEntity.setUserDelete(persistenceAuditorAware.getCurrentAuditor());
									em.merge(baseEntity);
								}
							}							
						} else {
							if (BaseEntity.class.isAssignableFrom(c)) {
								BaseEntity baseEntity = (BaseEntity)m.invoke(entity);
								baseEntity.setDateDelete(Calendar.getInstance().getTime());
								baseEntity.setUserDelete(persistenceAuditorAware.getCurrentAuditor());
								em.merge(baseEntity);
							}
						}						
					}					
				}
				
			} else {
				
				// hard delete
				em.remove(entity);
			}
		} catch (Exception e) {
			throw new OperationException(e);
		} finally {
			em.flush();
		}
	}
	
}
