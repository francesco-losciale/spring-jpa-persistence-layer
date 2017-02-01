package commons.model.operation;

import org.hibernate.Filter;

public interface IBaseReadEntityOperator {
	public Filter enableFilter(String filterName);
}
