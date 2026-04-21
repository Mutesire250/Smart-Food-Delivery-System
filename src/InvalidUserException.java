/**
 * Exception thrown when user-related data (name, email) is invalid.
 */
public class InvalidUserException extends DeliverySystemException {
    public InvalidUserException(String message) {
        super(message);
    }
}
