package com.persistence.operation;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import persistence2.helpers.exception.OperationException;

public interface IGetEntityOperation<EntityObjectType> {
	
	public EntityObjectType get(Class<EntityObjectType> resultClass, Object id) throws OperationException;

	public Query createJpqlQuery(String jpql);
	
	public Query createJpqlQuery(String jpql, Class<EntityObjectType> resultClass);
	
	public EntityManager getEntityManager(); // TODO remove
}
