/**
 * Exception thrown when delivery-related issues occur (blank address, delivery state).
 */
public class InvalidDeliveryException extends DeliverySystemException {
    public InvalidDeliveryException(String message) {
        super(message);
    }
}
