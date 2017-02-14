package commons.model.operation;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import commons.model.bean.OperationMetadata;
import commons.model.entity.BaseEntity;
import commons.model.entity.LogicDeleteEntity;
import commons.model.exception.OperationException;

@Repository
public class DeleteEntityOperation<E extends BaseEntity> extends BaseEntityOperator<E> implements IDeleteEntityOperation<E> {

	public final static String NAME = "deleteEntityOperation";

	public DeleteEntityOperation() {
		super();
	}

	private E completa(E entity, OperationMetadata operationMetadata) throws OperationException {

		String user = operationMetadata.getUsername();
		Date now = operationMetadata.getDateCalendar();

		if (entity instanceof LogicDeleteEntity) {

			LogicDeleteEntity logicDeleteEntity = (LogicDeleteEntity) entity;
			logicDeleteEntity.setDateDelete(now);
			logicDeleteEntity.setUserDelete(user);
		}

		entity.setUserModify(user);
		entity.setDateModify(now);

		return entity;
	}

	public List<E> evaluate(DetachedCriteria criteria, Class<?> cls) throws OperationException {
		return null;
	}

	@SuppressWarnings("unchecked")
	public E delete(E entity, OperationMetadata operationMetadata) throws OperationException {
		evaluate(entity, operationMetadata);
		try {

			E delete = (E) getSession().merge(completa(entity, operationMetadata));

			if (!(delete instanceof LogicDeleteEntity)) getSession().delete(delete);
			else getSession().update(delete);
			getSession().flush();

			return delete;
		} catch (Exception e) {
			throw new OperationException(e);
		}
	}
}
