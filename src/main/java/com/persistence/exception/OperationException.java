package com.persistence.exception;

public class OperationException extends GenericException {

	private static final long serialVersionUID = 1L;

	private Object operationInput;

	private Object operationOutput;

	public OperationException(Exception e) {
		super(e);
	}

	public OperationException(Exception e, Object operationInput, Object operationOutput) {
		this(e);
		this.operationInput = operationInput;
		this.operationOutput = operationOutput;
	}

	@Override
	public String getMessage() {

		StringBuilder builder = new StringBuilder(super.getMessage());
		builder.append(", operation input:");
		builder.append(operationInput);
		builder.append(", operation output:");
		builder.append(operationOutput);

		return builder.toString();
	}
}
