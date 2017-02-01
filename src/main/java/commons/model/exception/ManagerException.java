package commons.model.exception;

public class ManagerException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ManagerException(String msg) {
		super(msg);
	}

	public ManagerException(Throwable t) {
		super(t);
	}

	public ManagerException(Exception e) {
		super(e);
	}

	public ManagerException(String msg, Exception e) {
		super(msg, e);
	}
}
