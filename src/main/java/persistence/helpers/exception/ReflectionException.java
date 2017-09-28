package persistence.helpers.exception;

public class ReflectionException extends RuntimeException {
	public ReflectionException() {
		super();
	}

	public ReflectionException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public ReflectionException(final String message) {
		super(message);
	}

	public ReflectionException(final Throwable cause) {
		super(cause);
	}
}
