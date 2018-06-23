package exception;

public class DatabaseModificationException extends Exception {

    public DatabaseModificationException() {
        super();
    }

    public DatabaseModificationException(String message) {
        super(message);
    }
}
