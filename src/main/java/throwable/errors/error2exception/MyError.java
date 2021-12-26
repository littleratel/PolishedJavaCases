package throwable.errors.error2exception;

class MyError extends Error {

    private static final long serialVersionUID = 1L;

    public MyError() {
        super();
    }

    public MyError(String message, Throwable cause) {
        super(message, cause);
    }

    public MyError(String message) {
        super(message);
    }

    public MyError(Throwable cause) {
        super(cause);
    }
}