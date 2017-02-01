package commons.model.operation;

import java.util.List;

import commons.model.entity.IBaseEntity;
import commons.model.exception.NonUniqueResultException;
import commons.model.exception.OperationException;
import commons.model.expression.SimpleExpression;

public interface IEjbqlOperation<E extends IBaseEntity> extends IBaseReadEntityOperator {

	public List<E> getList(SimpleExpression<String> expression) throws OperationException;

	public Number getNumber(SimpleExpression<String> expression) throws OperationException, NonUniqueResultException;

	public E getSingle(SimpleExpression<String> expression) throws OperationException, NonUniqueResultException;

	public int execute(SimpleExpression<String> expression);
}
