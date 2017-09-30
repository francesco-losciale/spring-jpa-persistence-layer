package persistence2.helpers.util;

import java.util.Date;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

import persistence2.helpers.bean.OperationMetadata;
import persistence2.helpers.entity.BaseEntity;
import persistence2.helpers.entity.LogicDeleteEntity;

public class HibernateUtils {

	/**
	 * inizializzo entity e faccio restituire l'entity vera e propria
	 *
	 * @param <T>
	 * @param entity
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T initializeAndUnproxy(T entity) {

		initialize(entity);

		if (entity instanceof HibernateProxy) {
			entity = (T) ((HibernateProxy) entity).getHibernateLazyInitializer().getImplementation();
		}
		return entity;
	}

	/**
	 * inizializzo entity
	 *
	 * @param <T>
	 * @param entity
	 * @return
	 */
	public static <T> void initialize(T entity) {
		if (entity == null) {
			throw new NullPointerException("Entity passed for initialization is null");
		}

		Hibernate.initialize(entity);
	}

	/**
	 * Valorizza le proprietà di sistema per l'inserimento
	 *
	 * @param entity
	 * @param operationMetadata
	 */
	public static void prepareForInsert(BaseEntity entity, OperationMetadata operationMetadata) {

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
	}

	/**
	 * Valorizza le proprietà di sistema per la cancellazione
	 *
	 * @param entity
	 * @param operationMetadata
	 */
	public static void prepareForDelete(BaseEntity entity, OperationMetadata operationMetadata) {

		String user = operationMetadata.getUsername();
		Date now = operationMetadata.getDateCalendar();

		if (entity instanceof LogicDeleteEntity) {

			LogicDeleteEntity logicDeleteEntity = (LogicDeleteEntity) entity;
			logicDeleteEntity.setDateDelete(now);
			logicDeleteEntity.setUserDelete(user);
		}

		entity.setUserModify(user);
		entity.setDateModify(now);
	}
}
