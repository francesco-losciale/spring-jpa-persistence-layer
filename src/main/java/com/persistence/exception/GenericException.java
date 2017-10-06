package com.persistence.exception;

public class GenericException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public GenericException(String msg) {
		super(msg);
	}

	public GenericException(Throwable t) {
		super(t);
	}

	public GenericException(Exception e) {
		super(e);
	}

	public GenericException(String msg, Exception e) {
		super(msg, e);
	}
}
