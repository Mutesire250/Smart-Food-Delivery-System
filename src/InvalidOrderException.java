/**
 * Exception thrown when order data (ID, item name, price) is invalid.
 */
public class InvalidOrderException extends DeliverySystemException {
    public InvalidOrderException(String message) {
        super(message);
    }
}
