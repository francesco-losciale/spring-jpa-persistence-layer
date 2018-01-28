package com.persistence.operation;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;

import com.persistence.base.BasePersistenceProvider;
import com.persistence.exception.OperationException;

@Component
public class InsertEntityOperation<EntityObjectType> extends BasePersistenceProvider implements IInsertEntityOperation<EntityObjectType> {

	public InsertEntityOperation() {
		super();
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

}
