public class Delivery {
    private String location;
    private String status;
    private Order order;

    public Delivery(String location, Order order) {
        this.location = location;
        this.order = order;
        this.status = "Pending";
    }

    public void trackDelivery() {
        System.out.println("Tracking Order #" + order.getOrderId() + ": Current Status - " + status);
    }

    public void deliver() {
        this.status = "Out for Delivery";
        System.out.println("Delivery in progress to: " + location);
        
        this.status = "Delivered";
        System.out.println("Success! " + order.getItemName() + " has been delivered to " + location + ".");
    }

    public String getStatus() {
        return status;
    }
}
