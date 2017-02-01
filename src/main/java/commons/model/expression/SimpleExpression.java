package commons.model.expression;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import commons.model.bean.OperationMetadata;

public class SimpleExpression<T extends Serializable> extends BaseExpression {

	private T value;

	private Map<String, Object> params = new HashMap<String, Object>();

	public SimpleExpression(T value, OperationMetadata operationMetadata) {
		super(operationMetadata);
		this.value = value;
	}

	@Override
	public Object getExpression() {
		return value;
	}

	public T getValue() {
		return value;
	}

	@SuppressWarnings("unused")
	private void setValue(T value) {
		this.value = value;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public void addParam(String key, Object value) {
		this.params.put(key, value);
	}
}
