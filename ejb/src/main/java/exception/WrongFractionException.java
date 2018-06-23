package exception;

public class WrongFractionException extends RuntimeException {

    private static final long serialVersionUID = -8705001725049620610L;

    public WrongFractionException() {
        super();
    }

    public WrongFractionException(String message) {
        super(message);
    }
}
