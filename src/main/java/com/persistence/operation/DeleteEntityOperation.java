package com.persistence.operation;

import java.util.Calendar;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Repository;

import com.persistence.annotation.LogicalEntityDelete;
import com.persistence.base.BaseEntity;
import com.persistence.base.BasePersistenceProvider;
import com.persistence.exception.OperationException;

@Repository
public class DeleteEntityOperation<EntityObjectType extends BaseEntity> extends BasePersistenceProvider implements IDeleteEntityOperation<EntityObjectType> {

	public final static String NAME = "deleteEntityOperation";
	
	@Autowired
	private AuditorAware<String> persistenceAuditorAware;

	public DeleteEntityOperation() {
		super();
	}

	public void delete(EntityObjectType entity) throws OperationException {
		EntityManager em = getEntityManager();
		boolean isLogicalDelete = entity.getClass().isAnnotationPresent(LogicalEntityDelete.class); 
		try {
			if (isLogicalDelete) {
				entity.setDateDelete(Calendar.getInstance().getTime());
				entity.setUserDelete(persistenceAuditorAware.getCurrentAuditor());
				em.merge(entity);
			} else {
				em.remove(entity);
			}
		} catch (Exception e) {
			throw new OperationException(e);
		} finally {
			em.flush();
		}
	}
}
