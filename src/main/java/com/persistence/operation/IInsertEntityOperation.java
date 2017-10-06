package com.persistence.operation;

import com.persistence.exception.OperationException;

public interface IInsertEntityOperation<EntityObjectType> {

	public EntityObjectType insert(EntityObjectType entity) throws OperationException;

}
