package persistence.helpers.operation;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import persistence.helpers.bean.AllMetadata;
import persistence.helpers.entity.IBaseEntity;
import persistence.helpers.exception.NonUniqueResultException;

public interface ICriteriaOperation<E extends IBaseEntity> extends IBaseReadEntityOperator {

	public List<E> find(DetachedCriteria detachedCriteria);

	public E getSingle(DetachedCriteria detachedCriteria) throws NonUniqueResultException;

	public List<E> find(DetachedCriteria detachedCriteria, AllMetadata allMetadata);

	public <T> T executeSingle(DetachedCriteria detachedCriteria) throws NonUniqueResultException;

	public <T> List<T> execute(DetachedCriteria detachedCriteria);
}
