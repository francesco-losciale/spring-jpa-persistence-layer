package com.persistence.operation;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.persistence.base.BasePersistenceProvider;

import persistence2.helpers.exception.OperationException;

@Repository
public class UpdateEntityOperation<EntityObjectType> extends BasePersistenceProvider implements IUpdateEntityOperation<EntityObjectType> {

	public final static String NAME = "saveEntityOperation";

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
