import java.util.Scanner;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Customer myCustomer = null;
        Restaurant myRestaurant = new Restaurant("Miribe Kitchen");

        // Initializing a Map to represent a menu (Key-Value Relationship)
        myRestaurant.addMenuItem("Pizza", 12.99);
        myRestaurant.addMenuItem("Burger", 8.50);
        myRestaurant.addMenuItem("Pasta", 10.00);
        myRestaurant.addMenuItem("Salad", 7.25);

        System.out.println("--- Welcome to the Smart Food Delivery System ---");
        System.out.println();

        // 1. User Registration
        while (myCustomer == null) {
            try {
                System.out.print("Enter your name: ");
                String name = scanner.nextLine();
                System.out.print("Enter your email: ");
                String email = scanner.nextLine();

                myCustomer = new Customer(name, email);
                System.out.println("\nCustomer Profile Created Successfully!");
            } catch (InvalidUserException e) {
                System.out.println("Registration Error: " + e.getMessage());
            }
        }

        // 2. Display Menu (Map Retrieval)
        System.out.println("\nAvailable Menu:");
        for (Map.Entry<String, Double> entry : myRestaurant.getMenu().entrySet()) {
            System.out.println("- " + entry.getKey() + ": $" + entry.getValue());
        }

        // 3. Placing Multiple Orders (One-to-Many Relationship using List)
        System.out.println("\nHow many items would you like to order?");
        int count = 0;
        try {
            count = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number. Ordering 1 item by default.");
            count = 1;
        }

        for (int i = 0; i < count; i++) {
            System.out.print("Enter item name to order: ");
            String itemName = scanner.nextLine();
            Double price = myRestaurant.getPrice(itemName);

            if (price != null) {
                try {
                    Order order = myCustomer.placeOrder(200 + i, itemName, price);
                    myRestaurant.prepareOrder(order, myCustomer);
                } catch (Exception e) {
                    System.out.println("Error placing order: " + e.getMessage());
                }
            } else {
                System.out.println("Sorry, " + itemName + " is not on the menu.");
            }
        }

        // 4. View Order History (List Retrieval)
        System.out.println("\n--- Order History for " + myCustomer.getName() + " ---");
        List<Order> history = myCustomer.getOrderHistory();
        if (history.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            for (Order o : history) {
                System.out.println(o);
            }
        }

        // 5. Unique Customers (Set Demo)
        System.out.println("\n--- Restaurant Stats ---");
        System.out.println("Unique customers served: " + myRestaurant.getUniqueCustomerCount());

        // 6. Basic Operation: Removal demo
        System.out.println("\nRemoving 'Salad' from menu...");
        myRestaurant.removeMenuItem("Salad");
        System.out.println("New Menu count: " + myRestaurant.getMenu().size());

        System.out.println("\n--- End of Simulation ---");
        scanner.close();
    }
}
