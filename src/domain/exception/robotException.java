package domain.exception;

//custom exception class for toy robot simulator demo
public class robotException extends Exception {

    public robotException(String message) {
        super(message);
    }

    public robotException(String message, Throwable cause) {
        super(message, cause);
    }
}
