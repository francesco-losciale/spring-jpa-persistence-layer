package commons.model.operation;

import org.hibernate.Filter;

import commons.model.entity.BaseEntity;

public abstract class BaseReadEntityOperator<E extends BaseEntity> extends BaseEntityOperator<E> implements IBaseReadEntityOperator {

	public Filter enableFilter(String filterName) {
		return getSession().enableFilter(filterName);
	}
}
