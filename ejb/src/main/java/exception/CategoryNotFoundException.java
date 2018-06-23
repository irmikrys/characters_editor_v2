package exception;

public class CategoryNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -2341722651259733231L;

    public CategoryNotFoundException() {
        super();
    }

    public CategoryNotFoundException(String message) {
        super(message);
    }
}
