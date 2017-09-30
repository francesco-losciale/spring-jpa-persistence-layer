package persistence2.helpers.operation;

import persistence2.helpers.bean.OperationMetadata;
import persistence2.helpers.entity.IBaseEntity;
import persistence2.helpers.exception.OperationException;

public interface IDeleteEntityOperation<E extends IBaseEntity> {

	public E delete(E entity, OperationMetadata operationMetadata) throws OperationException;
}
