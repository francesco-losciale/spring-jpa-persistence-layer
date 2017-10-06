package com.persistence.operation;

import org.springframework.stereotype.Repository;

import com.persistence.base.BasePersistenceProvider;
import com.persistence.exception.OperationException;

@Repository
public class DeleteEntityOperation<EntityObjectType> extends BasePersistenceProvider implements IDeleteEntityOperation<EntityObjectType> {

	public final static String NAME = "deleteEntityOperation";

	public DeleteEntityOperation() {
		super();
	}

	public EntityObjectType delete(EntityObjectType entity) throws OperationException {
		throw new RuntimeException("not implemented yet");
	}
}
