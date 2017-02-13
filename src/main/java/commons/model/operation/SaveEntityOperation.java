package commons.model.operation;

import java.util.Date;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import commons.model.bean.OperationMetadata;
import commons.model.entity.BaseEntity;
import commons.model.exception.OperationException;

@Repository
@SuppressWarnings("unchecked")
public class SaveEntityOperation<E extends BaseEntity> extends BaseEntityOperator<E> implements ISaveEntityOperation<E> {

	public final static String NAME = "saveEntityOperation";

	public SaveEntityOperation() {
		super();
	}

	private E completa(E entity, OperationMetadata operationMetadata) {

		String user = operationMetadata.getUsername();
		Date now = operationMetadata.getDateCalendar();

		entity.setUserModify(user);
		if (entity.getUserInsert() == null) {
			entity.setUserInsert(user);
		}

		entity.setDateModify(now);
		if (entity.getDateInsert() == null) {
			entity.setDateInsert(now);
		}

		return entity;
	}

	public E save(E ent, OperationMetadata operationMetadata) throws OperationException {
		evaluate(ent, operationMetadata);
		E entity = completa(ent, operationMetadata);
		try {
			if (entity.getId() == null) getSession().saveOrUpdate(entity);
			else {
				entity = (E) getSession().merge(entity);
			}
			//getSession().flush();
		} catch (Exception e) {
			throw new OperationException(e);
		}

		return entity;
	}

	public Session getSession() {
		return super.getSession();
	}
}
