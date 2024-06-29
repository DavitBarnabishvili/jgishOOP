package Core.Domain.CustomException;

public class UserNotFoundException extends Exception {

    // Constructor with no arguments
    public UserNotFoundException() {
        super("User not found");
    }

    // Constructor with a custom message
    public UserNotFoundException(String message) {
        super(message);
    }

    // Constructor with a custom message and a cause
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor with a cause
    public UserNotFoundException(Throwable cause) {
        super(cause);
    }
}

