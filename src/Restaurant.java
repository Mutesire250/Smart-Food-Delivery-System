import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Restaurant {
    private String restaurantName;
    
    // Relationship: Key-Value lookup (Menu Item name -> Price)
    // Why Map? It allows for fast lookup of a specific item's price without iterating.
    private Map<String, Double> menu;

    // Relationship: Unique grouping (Set of unique customers served)
    // Why Set? It automatically handles uniqueness, ensuring each customer is counted only once.
    private Set<String> servedCustomerEmails;

    public Restaurant(String restaurantName) {
        this.restaurantName = restaurantName;
        this.menu = new HashMap<>();
        this.servedCustomerEmails = new HashSet<>();
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    // Basic Operation: Adding an element to a Map
    public void addMenuItem(String item, double price) {
        menu.put(item, price);
    }

    // Basic Operation: Retrieving an element from a Map
    public Double getPrice(String item) {
        return menu.get(item);
    }

    // Basic Operation: Removing an element from a Map
    public void removeMenuItem(String item) {
        menu.remove(item);
    }

    public void prepareOrder(Order order, Customer customer) {
        if (order == null || customer == null) {
            throw new IllegalArgumentException("Order and Customer cannot be null.");
        }
        
        // Basic Operation: Adding an element to a Set
        servedCustomerEmails.add(customer.getEmail());

        System.out.println(restaurantName + " is preparing " + order.getItemName() + " for " + customer.getName() + ".");
    }

    public int getUniqueCustomerCount() {
        // Basic Operation: Retrieving the size/elements of a Set
        return servedCustomerEmails.size();
    }

    public Map<String, Double> getMenu() {
        return new HashMap<>(menu);
    }
}
