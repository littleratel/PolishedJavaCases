package exceptions;

public class UnsupportedOperationException extends RuntimeException {
	private static final long serialVersionUID = -1242599979055084673L;

	public UnsupportedOperationException() {
	}

	/**
	 * Constructs an UnsupportedOperationException with the specified detail
	 * message.
	 *
	 * @param msg
	 *            the detail message
	 */
	public UnsupportedOperationException(String msg) {
		super(msg);
	}

}