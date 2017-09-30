package com.persistence.operation;

import persistence2.helpers.exception.OperationException;

public interface IInsertEntityOperation<EntityObjectType> {

	public EntityObjectType insert(EntityObjectType entity) throws OperationException;

}
