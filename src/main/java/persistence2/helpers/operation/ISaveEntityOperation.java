package persistence2.helpers.operation;

import org.hibernate.Session;

import persistence2.helpers.bean.OperationMetadata;
import persistence2.helpers.entity.IBaseEntity;
import persistence2.helpers.exception.OperationException;

public interface ISaveEntityOperation<E extends IBaseEntity> {

	public E save(E entity, OperationMetadata operationMetadata) throws OperationException;

}
