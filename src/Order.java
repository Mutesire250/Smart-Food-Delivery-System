public class Order {
    private int orderId;
    private String itemName;
    private double price;

    public Order(int orderId, String itemName, double price) {
        this.orderId = orderId;
        this.itemName = itemName;
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order #" + orderId + " [" + itemName + " - $" + price + "]";
    }
}
