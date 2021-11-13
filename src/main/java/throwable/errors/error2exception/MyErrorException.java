package throwable.errors.error2exception;

public class MyErrorException extends Exception {
    private static final long serialVersionUID = -584118464557310384L;

    public MyErrorException(final String msg) {
        super(msg);
    }
}
