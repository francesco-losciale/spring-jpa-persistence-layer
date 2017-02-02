package commons.model.util;

import java.io.Serializable;

class Result implements Serializable {

	public static final Result NO_RESULT = new Result(null, null);
	private static final long serialVersionUID = 9L;
	private Object value;

	private Object result;

	public Result(final Object value, final Object result) {
		super();
		this.value = value;
		this.result = result;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(final Object value) {
		this.value = value;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(final Object result) {
		this.result = result;
	}
}
