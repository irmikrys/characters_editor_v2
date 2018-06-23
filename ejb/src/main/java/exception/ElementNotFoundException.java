package exception;

public class ElementNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 5989633203373121613L;

    public ElementNotFoundException() {
        super();
    }

    public ElementNotFoundException(String message) {
        super(message);
    }
}
