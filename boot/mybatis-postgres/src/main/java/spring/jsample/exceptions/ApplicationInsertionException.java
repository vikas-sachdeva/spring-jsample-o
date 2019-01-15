package spring.jsample.exceptions;

public class ApplicationInsertionException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ApplicationInsertionException(String message) {
		super(message);
	}
}
