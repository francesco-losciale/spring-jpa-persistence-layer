package persistence2.helpers.exception;

public class ReflectionParserException extends Exception {

	private static final long serialVersionUID = 1L;

	public ReflectionParserException(final String msg) {
		super(msg);
	}

	public ReflectionParserException(final Throwable e) {
		super(e);
	}
}
