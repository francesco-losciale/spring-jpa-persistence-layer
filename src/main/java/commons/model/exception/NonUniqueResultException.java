package commons.model.exception;

public class NonUniqueResultException extends Exception {

	private static final long serialVersionUID = 1092132737489451806L;

	public NonUniqueResultException() {
		super();
	}

	public NonUniqueResultException(org.hibernate.NonUniqueResultException e) {
		super(e);
	}
}
