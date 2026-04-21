public class Order {
    private int orderId;
    private String itemName;
    private double price;

    public Order(int orderId, String itemName, double price) {
        validateOrderId(orderId);
        validateItemName(itemName);
        validatePrice(price);
        this.orderId = orderId;
        this.itemName = itemName;
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        validateOrderId(orderId);
        this.orderId = orderId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        validateItemName(itemName);
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        validatePrice(price);
        this.price = price;
    }

    private void validateOrderId(int orderId) {
        if (orderId <= 0) {
            throw new InvalidOrderException("Order ID must be positive.");
        }
    }

    private void validateItemName(String itemName) {
        if (itemName == null || itemName.trim().isEmpty()) {
            throw new InvalidOrderException("Item name cannot be empty.");
        }
    }

    private void validatePrice(double price) {
        if (price <= 0) {
            throw new InvalidOrderException("Price must be greater than zero.");
        }
    }

    @Override
    public String toString() {
        return "Order #" + orderId + " [" + itemName + " - $" + price + "]";
    }
}
