package com.persistence.operation;

import persistence2.helpers.exception.OperationException;

public interface IGetEntityOperation<EntityObjectType> {
	
	public EntityObjectType get(Class<EntityObjectType> resultClass, Long id) throws OperationException;
}
