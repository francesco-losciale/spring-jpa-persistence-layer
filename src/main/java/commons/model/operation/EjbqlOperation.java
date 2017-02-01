package commons.model.operation;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import commons.model.entity.BaseEntity;
import commons.model.exception.NonUniqueResultException;
import commons.model.exception.OperationException;
import commons.model.expression.SimpleExpression;

@Repository
public class EjbqlOperation<E extends BaseEntity> extends BaseReadEntityOperator<E> implements IEjbqlOperation<E> {

	public final static String NAME = "ejbqlOperation";

	public EjbqlOperation() {
		super();
	}

	public int execute(SimpleExpression<String> expression) {
		evaluate(expression);

		Query query = createQuery(expression);
		return query.executeUpdate();
	}

	public Number getNumber(SimpleExpression<String> expression) throws NonUniqueResultException {
		evaluate(expression);
		Query query = createQuery(expression);
		try {
			return (Number) query.uniqueResult();
		} catch (org.hibernate.NonUniqueResultException e) {
			throw new NonUniqueResultException(e);
		}
	}

	private int readRowCount(SimpleExpression<String> expression) {

		String ejbql = expression.getValue();
		SimpleExpression<String> expressionCount = new SimpleExpression<String>(ejbql, expression.getOperationMetadata());
		expressionCount.setParams(expression.getParams());

		Query query = createQuery(expressionCount);
		return query.list().size();
	}

	@SuppressWarnings("unchecked")
	public E getSingle(SimpleExpression<String> expression) throws OperationException, NonUniqueResultException {
		evaluate(expression);
		Query query = createQuery(expression);
		try {
			return (E) query.uniqueResult();
		} catch (org.hibernate.NonUniqueResultException e) {
			throw new NonUniqueResultException(e);
		}
	}

	private Query createQuery(SimpleExpression<String> expression) {
		Query query = getSession().createQuery(expression.getValue());

		Map<String, Object> params = expression.getParams();
		if (params != null) for (Entry<String, Object> item : params.entrySet()) {
			query.setParameter(item.getKey(), item.getValue());
		}

		return query;
	}

	@Override
	public List<E> getList(SimpleExpression<String> expression) throws OperationException {
		throw new RuntimeException("not implemented yet");
	}
}
