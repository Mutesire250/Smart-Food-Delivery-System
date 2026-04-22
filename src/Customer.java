import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    // Relationship: One-to-Many (One customer can have multiple orders)
    // Why List? It preserves the order of placement and allows duplicate items/orders.
    private List<Order> orderHistory;

    public Customer(String name, String email) {
        super(name, email);
        this.orderHistory = new ArrayList<>();
    }

    @Override
    public void displayInfo() {
        System.out.println("Customer Profile:");
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
    }

    public Order placeOrder(int orderId, String itemName, double price) {
        System.out.println(getName() + " is placing an order for " + itemName + ".");
        Order newOrder = new Order(orderId, itemName, price);
        
        // Basic Operation: Adding an element to the collection
        orderHistory.add(newOrder);
        
        return newOrder;
    }

    // Basic Operation: Retrieving elements from the collection
    public List<Order> getOrderHistory() {
        return new ArrayList<>(orderHistory); // Return a copy for encapsulation
    }

    // Basic Operation: Removing an element
    public void cancelOrder(Order order) {
        if (orderHistory.remove(order)) {
            System.out.println("Order #" + order.getOrderId() + " has been removed from history.");
        }
    }
}
