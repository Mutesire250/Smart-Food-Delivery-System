import java.util.Scanner;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Customer myCustomer = null;
        Restaurant myRestaurant = new Restaurant("Miribe Kitchen");

        // Initialize data persistence
        DataPersistence.initializeDataDirectory();
        System.out.println();

        // Initializing a Map to represent a menu (Key-Value Relationship)
        myRestaurant.addMenuItem("Pizza", 12.99);
        myRestaurant.addMenuItem("Burger", 8.50);
        myRestaurant.addMenuItem("Pasta", 10.00);
        myRestaurant.addMenuItem("Salad", 7.25);

        // Save menu to file
        DataPersistence.saveMenu(myRestaurant);

        System.out.println("--- Welcome to the Smart Food Delivery System ---");
        System.out.println();

        // Display user options
        boolean running = true;
        while (running) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Register/Login as Customer");
            System.out.println("2. Place Orders (existing customer)");
            System.out.println("3. View Order History");
            System.out.println("4. Create Delivery");
            System.out.println("5. View All Saved Data");
            System.out.println("6. Load Customers from File");
            System.out.println("7. Load Orders from File");
            System.out.println("8. Load Deliveries from File");
            System.out.println("9. Clear All Data Files");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    registerCustomer(scanner, myRestaurant);
                    myCustomer = getLastRegisteredCustomer();
                    break;
                case "2":
                    if (myCustomer != null) {
                        placeOrders(scanner, myCustomer, myRestaurant);
                    } else {
                        System.out.println("Please register first (Option 1).");
                    }
                    break;
                case "3":
                    if (myCustomer != null) {
                        viewOrderHistory(myCustomer);
                    } else {
                        System.out.println("Please register first (Option 1).");
                    }
                    break;
                case "4":
                    if (myCustomer != null) {
                        createDelivery(scanner, myCustomer);
                    } else {
                        System.out.println("Please register first (Option 1).");
                    }
                    break;
                case "5":
                    DataPersistence.displayAllData();
                    break;
                case "6":
                    List<Customer> customers = DataPersistence.loadCustomers();
                    System.out.println("Loaded customers:");
                    for (Customer c : customers) {
                        System.out.println("  - " + c.getName() + " (" + c.getEmail() + ")");
                    }
                    break;
                case "7":
                    List<Order> orders = DataPersistence.loadOrders();
                    System.out.println("Loaded orders:");
                    for (Order o : orders) {
                        System.out.println("  - " + o);
                    }
                    break;
                case "8":
                    List<Map<String, String>> deliveries = DataPersistence.loadDeliveries();
                    System.out.println("Loaded deliveries:");
                    for (Map<String, String> d : deliveries) {
                        System.out.println("  - Location: " + d.get("location") + 
                                         ", Status: " + d.get("status"));
                    }
                    break;
                case "9":
                    DataPersistence.clearAllData();
                    break;
                case "0":
                    running = false;
                    System.out.println("Thank you for using Smart Food Delivery System!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        
        scanner.close();
    }

    /**
     * Register a customer and save to file
     */
    private static void registerCustomer(Scanner scanner, Restaurant restaurant) {
        try {
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            System.out.print("Enter your email: ");
            String email = scanner.nextLine();

            Customer customer = new Customer(name, email);
            System.out.println("\n✓ Customer Profile Created Successfully!");
            
            // Save customer to file
            DataPersistence.saveCustomer(customer);
            
        } catch (InvalidUserException e) {
            System.out.println("Registration Error: " + e.getMessage());
        }
    }

    /**
     * Get the last registered customer (simulated)
     */
    private static Customer getLastRegisteredCustomer() {
        List<Customer> customers = DataPersistence.loadCustomers();
        if (!customers.isEmpty()) {
            return customers.get(customers.size() - 1);
        }
        return null;
    }

    /**
     * Place orders for a customer
     */
    private static void placeOrders(Scanner scanner, Customer customer, Restaurant restaurant) {
        System.out.println("\nAvailable Menu:");
        for (Map.Entry<String, Double> entry : restaurant.getMenu().entrySet()) {
            System.out.println("- " + entry.getKey() + ": $" + entry.getValue());
        }

        System.out.print("\nHow many items would you like to order? ");
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
            Double price = restaurant.getPrice(itemName);

            if (price != null) {
                try {
                    Order order = customer.placeOrder(200 + i, itemName, price);
                    restaurant.prepareOrder(order, customer);
                    
                    // Save order to file
                    DataPersistence.saveOrder(order, customer.getName());
                    
                } catch (Exception e) {
                    System.out.println("Error placing order: " + e.getMessage());
                }
            } else {
                System.out.println("Sorry, " + itemName + " is not on the menu.");
            }
        }
    }

    /**
     * View order history and optionally save to file
     */
    private static void viewOrderHistory(Customer customer) {
        System.out.println("\n--- Order History for " + customer.getName() + " ---");
        List<Order> history = customer.getOrderHistory();
        if (history.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            for (Order o : history) {
                System.out.println(o);
            }
        }
    }

    /**
     * Create and process a delivery
     */
    private static void createDelivery(Scanner scanner, Customer customer) {
        System.out.print("Enter delivery location: ");
        String location = scanner.nextLine();
        
        List<Order> orders = customer.getOrderHistory();
        if (orders.isEmpty()) {
            System.out.println("No orders to deliver.");
            return;
        }
        
        try {
            Delivery delivery = new Delivery(location, orders.get(0));
            delivery.trackDelivery();
            delivery.deliver();
            
            // Save delivery to file
            DataPersistence.saveDelivery(delivery, customer.getName());
            
        } catch (InvalidDeliveryException e) {
            System.out.println("Delivery Error: " + e.getMessage());
        }
    }
}
