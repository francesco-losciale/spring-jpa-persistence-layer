package persistence.helpers.exception;

public class ParserException extends RuntimeException {
	public ParserException() {
		super();
	}

	public ParserException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public ParserException(final String message) {
		super(message);
	}

	public ParserException(final Throwable cause) {
		super(cause);
	}
}
