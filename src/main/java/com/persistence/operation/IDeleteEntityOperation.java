package com.persistence.operation;

import com.persistence.exception.OperationException;

public interface IDeleteEntityOperation<EntityObjectType> {

	public EntityObjectType delete(EntityObjectType entity) throws OperationException;
}
