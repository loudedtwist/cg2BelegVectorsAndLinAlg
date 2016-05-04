package exceptions;


public class DoublesOutOfRangeException extends RuntimeException{
    public DoublesOutOfRangeException(String message) {
        super(message);
    }

    public DoublesOutOfRangeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DoublesOutOfRangeException(Throwable cause) {
        super(cause);
    }

    protected DoublesOutOfRangeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
