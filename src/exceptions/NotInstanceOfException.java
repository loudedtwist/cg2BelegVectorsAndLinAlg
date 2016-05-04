package exceptions;

public class NotInstanceOfException extends RuntimeException{
    public NotInstanceOfException(String message) {
        super(message);
    }

    public NotInstanceOfException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotInstanceOfException(Throwable cause) {
        super(cause);
    }

    protected NotInstanceOfException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
