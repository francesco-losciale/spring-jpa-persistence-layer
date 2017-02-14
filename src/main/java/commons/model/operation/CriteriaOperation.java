package commons.model.operation;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.internal.CriteriaImpl;
import org.springframework.stereotype.Repository;

import commons.model.bean.AllMetadata;
import commons.model.entity.BaseEntity;
import commons.model.exception.NonUniqueResultException;
import commons.model.exception.OperationException;

@SuppressWarnings("unchecked")
@Repository
public class CriteriaOperation<E extends BaseEntity> extends BaseReadEntityOperator<E> implements ICriteriaOperation<E> {

	public final static String NAME = "criteriaOperation";

	public CriteriaOperation() {
		super();
	}

	public List<E> find(DetachedCriteria detachedCriteria) {
		List<E> list = null;
		try {
			Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
			list = criteria.list();
		} catch (Exception e) {
			throw new OperationException(e);
		}

		return list;
	}

	public E getSingle(DetachedCriteria detachedCriteria) throws NonUniqueResultException {
		List<E> list = find(detachedCriteria);

		if (list == null || list.isEmpty()) return null;

		if (list.size() > 1) throw new NonUniqueResultException();

		E e = list.get(0);

		return e;
	}

	public <T> T executeSingle(DetachedCriteria detachedCriteria) throws NonUniqueResultException {

		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		List<T> list = criteria.list();

		if (list == null || list.isEmpty()) return null;

		if (list.size() > 1) throw new NonUniqueResultException();

		T t = (T) list.get(0);

		return t;
	}

	public <T> List<T> execute(DetachedCriteria detachedCriteria) {

		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		List<T> list = criteria.list();

		return list;
	}

	private int readRowCount(DetachedCriteria detachedCriteria) {
		Criteria criteria = this.getCriteria(detachedCriteria);

		Projection realProjection = this.getProjection(criteria);

		// criteria =
		// criteria.setProjection(Projections.countDistinct("entityPartenza.id"));
		criteria = criteria.setProjection(Projections.rowCount());

		Object result = null;
		try {
			List<E> tmp = criteria.list();

			result = tmp.get(0);
		} catch (Exception e) {
			throw e;
		}

		int count = 0;

		if (result instanceof Long) {
			count = ((Long) result).intValue();
		} else if (result instanceof Integer) {
			count = ((Integer) result).intValue();
		}

		criteria.setProjection(realProjection);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		return count;
	}

	private Projection getProjection(Criteria criteria) {
		Projection realProjection = ((CriteriaImpl) criteria).getProjection();
		return realProjection;
	}

	private Criteria getCriteria(DetachedCriteria detachedCriteria) {
		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		return criteria;
	}

	@Override
	public List<E> find(DetachedCriteria detachedCriteria, AllMetadata allMetadata) {
		throw new RuntimeException("not implemented yet");
	}

}

