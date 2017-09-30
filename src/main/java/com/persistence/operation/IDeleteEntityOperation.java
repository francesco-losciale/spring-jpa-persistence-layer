package com.persistence.operation;

import persistence2.helpers.exception.OperationException;

public interface IDeleteEntityOperation<EntityObjectType> {

	public EntityObjectType delete(EntityObjectType entity) throws OperationException;
}
