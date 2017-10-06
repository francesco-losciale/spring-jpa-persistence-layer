package com.persistence.operation;

import javax.persistence.EntityManager;

import com.persistence.exception.OperationException;

public interface IGetEntityOperation<EntityObjectType> {
	
	public EntityObjectType get(Class<EntityObjectType> resultClass, Object id) throws OperationException;
	
	public EntityManager getEntityManager(); 
}
