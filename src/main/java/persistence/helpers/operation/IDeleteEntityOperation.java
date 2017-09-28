package persistence.helpers.operation;

import persistence.helpers.bean.OperationMetadata;
import persistence.helpers.entity.IBaseEntity;
import persistence.helpers.exception.OperationException;

public interface IDeleteEntityOperation<E extends IBaseEntity> {

	public E delete(E entity, OperationMetadata operationMetadata) throws OperationException;
}
