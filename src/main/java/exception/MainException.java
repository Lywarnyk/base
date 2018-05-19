package exception;

/**
 * An exception that provides information on an application error.
 *
 * @author D.Kuliha
 *
 */
public class MainException extends RuntimeException {

    private static final long serialVersionUID = 2298217204022669546L;

    public MainException() {
        super();
    }

    public MainException(String message, Throwable cause) {
        super(message, cause);
    }

    public MainException(String message) {
        super(message);
    }}
