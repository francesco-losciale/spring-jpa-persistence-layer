package persistence.helpers.operation;

import org.hibernate.Session;

import persistence.helpers.bean.OperationMetadata;
import persistence.helpers.entity.IBaseEntity;
import persistence.helpers.exception.OperationException;

public interface ISaveEntityOperation<E extends IBaseEntity> {

	public E save(E entity, OperationMetadata operationMetadata) throws OperationException;

}
