package commons.model.expression;

import commons.model.bean.OperationMetadata;

public abstract class BaseExpression implements Expression {

	protected OperationMetadata operationMetadata;

	public BaseExpression(OperationMetadata operationMetadata) {
		this.operationMetadata = operationMetadata;
	}

	public abstract Object getExpression();

	public OperationMetadata getOperationMetadata() {
		return operationMetadata;
	}

	@SuppressWarnings("unused")
	private void setOperationMetadata(OperationMetadata operationMetadata) {
		this.operationMetadata = operationMetadata;
	}
}
