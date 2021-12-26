package connectionpool.eric.exception;

public class AcquireObjectFailedException extends RuntimeException {
    private static final long serialVersionUID = 1608519651123L;

    public AcquireObjectFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AcquireObjectFailedException(InterruptedException e) {
        super(e);
    }
}
