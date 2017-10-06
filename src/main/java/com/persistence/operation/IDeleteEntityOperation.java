package com.persistence.operation;

import com.persistence.exception.OperationException;

public interface IDeleteEntityOperation<EntityObjectType> {

	public void delete(EntityObjectType entity) throws OperationException;
}
