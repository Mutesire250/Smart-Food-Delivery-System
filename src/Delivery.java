public class Delivery {
    private String location;
    private String status;
    private Order order;

    public Delivery(String location, Order order) {
        if (location == null || location.trim().isEmpty()) {
            throw new InvalidDeliveryException("Delivery location cannot be empty.");
        }
        if (order == null) {
            throw new InvalidDeliveryException("Order cannot be null for delivery.");
        }
        this.location = location;
        this.order = order;
        this.status = "Pending";
    }

    public void trackDelivery() {
        System.out.println("Tracking Order " + order.getOrderId() + ": Current Status - " + status);
    }

    public void deliver() {
        if ("Delivered".equals(this.status)) {
            throw new InvalidDeliveryException("Order #" + order.getOrderId() + " has already been delivered.");
        }
        this.status = "Out for Delivery";
        System.out.println("Delivery in progress to: " + location);
        
        this.status = "Delivered";
        System.out.println("Success! " + order.getItemName() + " has been delivered to " + location + ".");
    }

    public String getStatus() {
        return status;
    }
}
