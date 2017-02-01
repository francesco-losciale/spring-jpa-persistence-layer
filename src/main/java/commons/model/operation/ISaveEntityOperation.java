package commons.model.operation;

import org.hibernate.Session;

import commons.model.bean.OperationMetadata;
import commons.model.entity.IBaseEntity;
import commons.model.exception.OperationException;

public interface ISaveEntityOperation<E extends IBaseEntity> {

	public E save(E entity, OperationMetadata operationMetadata) throws OperationException;

	public Session getSession();
}
