/**
 * Base custom exception for the Smart Food Delivery System.
 * Inherits from RuntimeException (Unchecked Exception).
 */
public class DeliverySystemException extends RuntimeException {
    public DeliverySystemException(String message) {
        super(message);
    }
}
