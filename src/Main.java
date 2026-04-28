import java.util.Scanner;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

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

        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║   🚀 SMART FOOD DELIVERY SYSTEM 🚀     ║");
        System.out.println("║                                        ║");
        System.out.println("║   Welcome to " + myRestaurant.getRestaurantName() + "!           ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println();

        // Display user options
        boolean running = true;
        while (running) {
            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║   🚀 SMART FOOD DELIVERY SYSTEM 🚀     ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.println("1️⃣  Register/Login as Customer");
            System.out.println("2️⃣  Place Orders");
            System.out.println("3️⃣  View Order History");
            System.out.println("4️⃣  Create Delivery");
            System.out.println("5️⃣  View All Saved Data");
            System.out.println("6️⃣  Load Customers from File");
            System.out.println("7️⃣  Load Orders from File");
            System.out.println("8️⃣  Load Deliveries from File");
            System.out.println("9️⃣  Clear All Data Files");
            System.out.println("0️⃣  Exit");
            System.out.print("\n👉 Choose an option (0-9): ");
            
            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1":
                    registerCustomer(scanner, myRestaurant);
                    myCustomer = getLastRegisteredCustomer();
                    break;
                case "2":
                    if (myCustomer != null) {
                        placeOrders(scanner, myCustomer, myRestaurant);
                    } else {
                        System.out.println("\n❌ Please register first (Option 1).");
                    }
                    break;
                case "3":
                    if (myCustomer != null) {
                        viewOrderHistory(myCustomer);
                    } else {
                        System.out.println("\n❌ Please register first (Option 1).");
                    }
                    break;
                case "4":
                    if (myCustomer != null) {
                        createDelivery(scanner, myCustomer);
                    } else {
                        System.out.println("\n❌ Please register first (Option 1).");
                    }
                    break;
                case "5":
                    DataPersistence.displayAllData();
                    break;
                case "6":
                    List<Customer> customers = DataPersistence.loadCustomers();
                    System.out.println("\n📋 Loaded customers:");
                    for (Customer c : customers) {
                        System.out.println("  👤 " + c.getName() + " (" + c.getEmail() + ")");
                    }
                    break;
                case "7":
                    List<Order> orders = DataPersistence.loadOrders();
                    System.out.println("\n📋 Loaded orders:");
                    for (Order o : orders) {
                        System.out.println("  🛒 " + o);
                    }
                    break;
                case "8":
                    List<Map<String, String>> deliveries = DataPersistence.loadDeliveries();
                    System.out.println("\n📋 Loaded deliveries:");
                    for (Map<String, String> d : deliveries) {
                        System.out.println("  🚗 Location: " + d.get("location") + 
                                         ", Status: " + d.get("status"));
                    }
                    break;
                case "9":
                    System.out.print("\n⚠️  Are you sure? This will delete all data (yes/no): ");
                    if (scanner.nextLine().trim().toLowerCase().equals("yes")) {
                        DataPersistence.clearAllData();
                    }
                    break;
                case "0":
                    running = false;
                    System.out.println("\n👋 Thank you for using Smart Food Delivery System!");
                    break;
                default:
                    System.out.println("\n❌ Invalid option. Please try again (0-9).");
            }
        }
        
        scanner.close();
    }

    /**
     * Register a customer and save to file
     */
    private static void registerCustomer(Scanner scanner, Restaurant restaurant) {
        try {
            System.out.println("\n╔══════════════════════════════════╗");
            System.out.println("║   📝 CUSTOMER REGISTRATION        ║");
            System.out.println("╚══════════════════════════════════╝");
            
            System.out.print("👤 Enter your full name: ");
            String name = scanner.nextLine().trim();
            System.out.print("📧 Enter your email address: ");
            String email = scanner.nextLine().trim();

            Customer customer = new Customer(name, email);
            System.out.println("\n✅ Customer Profile Created Successfully!");
            System.out.println("   Welcome, " + name + "!");
            
            // Save customer to file
            DataPersistence.saveCustomer(customer);
            
        } catch (InvalidUserException e) {
            System.out.println("\n❌ Registration Error: " + e.getMessage());
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
     * Place orders for a customer - Direct selection from menu
     */
    private static void placeOrders(Scanner scanner, Customer customer, Restaurant restaurant) {
        int orderCounter = 200;
        boolean continueShopping = true;
        
        while (continueShopping) {
            // Display menu with numbers
            System.out.println("\n╔══════════════════════════════════╗");
            System.out.println("║      🍽️  MENU - SELECT BY NUMBER    ║");
            System.out.println("╚══════════════════════════════════╝");
            
            Map<Integer, String> menuMap = new HashMap<>();
            Map<Integer, Double> priceMap = new HashMap<>();
            int itemNumber = 1;
            
            for (Map.Entry<String, Double> entry : restaurant.getMenu().entrySet()) {
                menuMap.put(itemNumber, entry.getKey());
                priceMap.put(itemNumber, entry.getValue());
                System.out.println(itemNumber + ". " + entry.getKey() + " ..................... $" + 
                                 String.format("%.2f", entry.getValue()));
                itemNumber++;
            }
            
            System.out.println("0. Done Ordering");
            System.out.print("\n👉 Select item number (0 to finish): ");
            
            String choice = scanner.nextLine().trim();
            
            try {
                int selectedNumber = Integer.parseInt(choice);
                
                if (selectedNumber == 0) {
                    continueShopping = false;
                    System.out.println("\n✓ Thank you for your order!");
                    break;
                }
                
                if (selectedNumber > 0 && selectedNumber < itemNumber) {
                    String itemName = menuMap.get(selectedNumber);
                    Double price = priceMap.get(selectedNumber);
                    
                    System.out.print("How many " + itemName + "? Enter quantity: ");
                    int quantity = 1;
                    try {
                        quantity = Integer.parseInt(scanner.nextLine().trim());
                        if (quantity <= 0) {
                            System.out.println("❌ Quantity must be greater than 0");
                            continue;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("⚠️  Invalid quantity. Ordering 1 item.");
                        quantity = 1;
                    }
                    
                    // Place order(s) for the selected quantity
                    for (int q = 0; q < quantity; q++) {
                        try {
                            Order order = customer.placeOrder(orderCounter, itemName, price);
                            restaurant.prepareOrder(order, customer);
                            DataPersistence.saveOrder(order, customer.getName());
                            orderCounter++;
                            
                            if (quantity == 1) {
                                System.out.println("\n✅ Order placed: " + itemName + " - $" + 
                                                 String.format("%.2f", price));
                            } else if (q == quantity - 1) {
                                System.out.println("\n✅ Order placed: " + quantity + "x " + itemName + 
                                                 " - $" + String.format("%.2f", price * quantity));
                            }
                        } catch (Exception e) {
                            System.out.println("❌ Error placing order: " + e.getMessage());
                        }
                    }
                    
                    System.out.print("\nWould you like to order more? (yes/no): ");
                    String response = scanner.nextLine().trim().toLowerCase();
                    if (!response.equals("yes") && !response.equals("y")) {
                        continueShopping = false;
                        System.out.println("\n✓ Order completed! Thank you!");
                    }
                } else {
                    System.out.println("❌ Invalid selection. Please choose a valid item number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Please enter a valid number.");
            }
        }
    }

    /**
     * View order history and optionally save to file
     */
    private static void viewOrderHistory(Customer customer) {
        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║   📋 ORDER HISTORY                 ║");
        System.out.println("╚══════════════════════════════════╝");
        System.out.println("Customer: " + customer.getName());
        
        List<Order> history = customer.getOrderHistory();
        if (history.isEmpty()) {
            System.out.println("\n❌ No orders found. Place an order to get started!");
        } else {
            System.out.println("\nTotal Orders: " + history.size());
            double totalPrice = 0;
            for (Order o : history) {
                System.out.println("  🛒 " + o);
                totalPrice += o.getPrice();
            }
            System.out.println("\n💰 Total Amount: $" + String.format("%.2f", totalPrice));
        }
    }

    /**
     * Create and process a delivery
     */
    private static void createDelivery(Scanner scanner, Customer customer) {
        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║   🚗 CREATE DELIVERY              ║");
        System.out.println("╚══════════════════════════════════╝");
        
        List<Order> orders = customer.getOrderHistory();
        if (orders.isEmpty()) {
            System.out.println("\n❌ No orders to deliver. Place an order first!");
            return;
        }
        
        System.out.println("\nOrders available for delivery:");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println((i + 1) + ". " + orders.get(i));
        }
        
        System.out.print("\nSelect order number to deliver (1-" + orders.size() + "): ");
        int orderChoice = 0;
        try {
            orderChoice = Integer.parseInt(scanner.nextLine().trim()) - 1;
            if (orderChoice < 0 || orderChoice >= orders.size()) {
                System.out.println("❌ Invalid selection.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Please enter a valid number.");
            return;
        }
        
        System.out.print("📍 Enter delivery location (address): ");
        String location = scanner.nextLine().trim();
        
        if (location.isEmpty()) {
            System.out.println("❌ Location cannot be empty.");
            return;
        }
        
        try {
            Delivery delivery = new Delivery(location, orders.get(orderChoice));
            delivery.trackDelivery();
            delivery.deliver();
            
            // Save delivery to file
            DataPersistence.saveDelivery(delivery, customer.getName());
            System.out.println("\n✅ Delivery saved successfully!");
            
        } catch (InvalidDeliveryException e) {
            System.out.println("\n❌ Delivery Error: " + e.getMessage());
        }
    }
}
