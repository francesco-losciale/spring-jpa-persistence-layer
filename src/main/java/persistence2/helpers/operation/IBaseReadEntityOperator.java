package persistence2.helpers.operation;

import org.hibernate.Filter;

public interface IBaseReadEntityOperator {
	public Filter enableFilter(String filterName);
}
