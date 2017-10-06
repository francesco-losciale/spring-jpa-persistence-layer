package com.persistence.operation;

import com.persistence.exception.OperationException;

public interface IUpdateEntityOperation<EntityObjectType> {

	public EntityObjectType update(EntityObjectType entity) throws OperationException;

}
