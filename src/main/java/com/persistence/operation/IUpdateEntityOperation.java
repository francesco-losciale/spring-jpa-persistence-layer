package com.persistence.operation;

import persistence2.helpers.exception.OperationException;

public interface IUpdateEntityOperation<EntityObjectType> {

	public EntityObjectType update(EntityObjectType entity) throws OperationException;

}
