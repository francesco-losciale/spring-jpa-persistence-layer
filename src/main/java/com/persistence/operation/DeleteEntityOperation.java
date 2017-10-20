package com.persistence.operation;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Repository;

import com.persistence.annotation.SoftDeleteActive;
import com.persistence.base.BaseEntity;
import com.persistence.base.BasePersistenceProvider;
import com.persistence.exception.OperationException;

@Repository
public class DeleteEntityOperation<EntityObjectType extends BaseEntity> extends BasePersistenceProvider implements IDeleteEntityOperation<EntityObjectType> {
	
	@Autowired
	private AuditorAware<String> persistenceAuditorAware;

	public DeleteEntityOperation() {
		super();
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
