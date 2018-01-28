package com.persistence.operation;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;

import com.persistence.base.BasePersistenceProvider;
import com.persistence.exception.OperationException;

@Component
public class UpdateEntityOperation<EntityObjectType> extends BasePersistenceProvider implements IUpdateEntityOperation<EntityObjectType> {

	public UpdateEntityOperation() {
		super();
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

}
