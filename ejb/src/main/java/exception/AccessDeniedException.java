package exception;

public class AccessDeniedException extends RuntimeException {

    private static final long serialVersionUID = 4342330123788659205L;

    public AccessDeniedException() {
        super();
    }

    public AccessDeniedException(String message) {
        super(message);
    }
}
