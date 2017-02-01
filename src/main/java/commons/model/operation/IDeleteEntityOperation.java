package commons.model.operation;

import commons.model.bean.OperationMetadata;
import commons.model.entity.IBaseEntity;
import commons.model.exception.OperationException;

public interface IDeleteEntityOperation<E extends IBaseEntity> {

	public E delete(E entity, OperationMetadata operationMetadata) throws OperationException;
}
