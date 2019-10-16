package exceptions;

public class ResourceManagerException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ResourceManagerException(String message) {
		super(message);
	}

	public ResourceManagerException(Throwable cause) {
		super(cause);
	}

	public ResourceManagerException(String message, Throwable cause) {
		super(message, cause);
	}
}
