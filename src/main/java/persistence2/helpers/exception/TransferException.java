package persistence2.helpers.exception;

public class TransferException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TransferException() {
		super();
	}

	public TransferException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public TransferException(final String message) {
		super(message);
	}

	public TransferException(final Throwable cause) {
		super(cause);
	}
}
