package exception;

public class WrongTypeSetException extends RuntimeException {

    public WrongTypeSetException() {
        super();
    }

    public WrongTypeSetException(String message) {
        super(message);
    }
}
