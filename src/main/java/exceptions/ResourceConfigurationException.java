package exceptions;

public class ResourceConfigurationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceConfigurationException(String message) {
        super(message);
    }

    public ResourceConfigurationException(Throwable cause) {
        super(cause);
    }

    public ResourceConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}
