package com.persistence.operation;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.persistence.base.BasePersistenceProvider;
import com.persistence.exception.OperationException;

@Repository
public class InsertEntityOperation<EntityObjectType> extends BasePersistenceProvider implements IInsertEntityOperation<EntityObjectType> {

	public final static String NAME = "saveEntityOperation";

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