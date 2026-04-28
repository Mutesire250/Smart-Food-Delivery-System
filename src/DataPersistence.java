import java.io.*;
import java.util.*;

/**
 * DataPersistence class handles reading and writing data to/from files.
 * Uses CSV format for easy data serialization and deserialization.
 */
public class DataPersistence {
    private static final String DATA_DIR = "data";
    private static final String CUSTOMERS_FILE = "customers.csv";
    private static final String ORDERS_FILE = "orders.csv";
    private static final String DELIVERIES_FILE = "deliveries.csv";
    private static final String MENU_FILE = "menu.csv";

    /**
     * Initialize data directory
     */
    public static void initializeDataDirectory() {
        File directory = new File(DATA_DIR);
        if (!directory.exists()) {
            directory.mkdir();
            System.out.println("Data directory created: " + DATA_DIR);
        }
    }

    /**
     * Save a customer to file
     */
    public static void saveCustomer(Customer customer) {
        initializeDataDirectory();
        try (FileWriter writer = new FileWriter(getFilePath(CUSTOMERS_FILE), true);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            
            // Check if file is empty to add header
            File file = new File(getFilePath(CUSTOMERS_FILE));
            if (file.length() == 0) {
                bufferedWriter.write("Name,Email,OrderCount\n");
            }
            
            // Write customer data
            String line = customer.getName() + "," + customer.getEmail() + "," + 
                         customer.getOrderHistory().size();
            bufferedWriter.write(line + "\n");
            bufferedWriter.flush();
            
            System.out.println("✓ Customer saved: " + customer.getName());
        } catch (IOException e) {
            System.err.println("Error saving customer: " + e.getMessage());
        }
    }

    /**
     * Load all customers from file
     */
    public static List<Customer> loadCustomers() {
        List<Customer> customers = new ArrayList<>();
        File file = new File(getFilePath(CUSTOMERS_FILE));
        
        if (!file.exists()) {
            System.out.println("No customer file found.");
            return customers;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isHeader = true;
            
            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                
                String[] data = line.split(",");
                if (data.length >= 2) {
                    try {
                        Customer customer = new Customer(data[0], data[1]);
                        customers.add(customer);
                    } catch (InvalidUserException e) {
                        System.err.println("Error loading customer: " + e.getMessage());
                    }
                }
            }
            
            System.out.println("✓ Loaded " + customers.size() + " customers from file");
        } catch (IOException e) {
            System.err.println("Error loading customers: " + e.getMessage());
        }
        
        return customers;
    }

    /**
     * Save an order to file
     */
    public static void saveOrder(Order order, String customerName) {
        initializeDataDirectory();
        try (FileWriter writer = new FileWriter(getFilePath(ORDERS_FILE), true);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            
            // Check if file is empty to add header
            File file = new File(getFilePath(ORDERS_FILE));
            if (file.length() == 0) {
                bufferedWriter.write("OrderID,ItemName,Price,CustomerName,Timestamp\n");
            }
            
            String line = order.getOrderId() + "," + order.getItemName() + "," + 
                         order.getPrice() + "," + customerName + "," + 
                         System.currentTimeMillis();
            bufferedWriter.write(line + "\n");
            bufferedWriter.flush();
            
            System.out.println("✓ Order saved: " + order);
        } catch (IOException e) {
            System.err.println("Error saving order: " + e.getMessage());
        }
    }

    /**
     * Load all orders from file
     */
    public static List<Order> loadOrders() {
        List<Order> orders = new ArrayList<>();
        File file = new File(getFilePath(ORDERS_FILE));
        
        if (!file.exists()) {
            System.out.println("No orders file found.");
            return orders;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isHeader = true;
            
            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                
                String[] data = line.split(",");
                if (data.length >= 3) {
                    try {
                        Order order = new Order(Integer.parseInt(data[0]), data[1], 
                                              Double.parseDouble(data[2]));
                        orders.add(order);
                    } catch (NumberFormatException | InvalidOrderException e) {
                        System.err.println("Error loading order: " + e.getMessage());
                    }
                }
            }
            
            System.out.println("✓ Loaded " + orders.size() + " orders from file");
        } catch (IOException e) {
            System.err.println("Error loading orders: " + e.getMessage());
        }
        
        return orders;
    }

    /**
     * Save a delivery to file
     */
    public static void saveDelivery(Delivery delivery, String customerName) {
        initializeDataDirectory();
        try (FileWriter writer = new FileWriter(getFilePath(DELIVERIES_FILE), true);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            
            // Check if file is empty to add header
            File file = new File(getFilePath(DELIVERIES_FILE));
            if (file.length() == 0) {
                bufferedWriter.write("Location,Status,CustomerName,Timestamp\n");
            }
            
            String line = delivery.getLocation() + "," + delivery.getStatus() + "," + 
                         customerName + "," + System.currentTimeMillis();
            bufferedWriter.write(line + "\n");
            bufferedWriter.flush();
            
            System.out.println("✓ Delivery saved: Location=" + delivery.getLocation() + 
                             ", Status=" + delivery.getStatus());
        } catch (IOException e) {
            System.err.println("Error saving delivery: " + e.getMessage());
        }
    }

    /**
     * Load all deliveries from file
     */
    public static List<Map<String, String>> loadDeliveries() {
        List<Map<String, String>> deliveries = new ArrayList<>();
        File file = new File(getFilePath(DELIVERIES_FILE));
        
        if (!file.exists()) {
            System.out.println("No deliveries file found.");
            return deliveries;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isHeader = true;
            
            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                
                String[] data = line.split(",");
                if (data.length >= 2) {
                    Map<String, String> delivery = new HashMap<>();
                    delivery.put("location", data[0]);
                    delivery.put("status", data[1]);
                    if (data.length > 2) delivery.put("customer", data[2]);
                    deliveries.add(delivery);
                }
            }
            
            System.out.println("✓ Loaded " + deliveries.size() + " deliveries from file");
        } catch (IOException e) {
            System.err.println("Error loading deliveries: " + e.getMessage());
        }
        
        return deliveries;
    }

    /**
     * Save restaurant menu to file
     */
    public static void saveMenu(Restaurant restaurant) {
        initializeDataDirectory();
        try (FileWriter writer = new FileWriter(getFilePath(MENU_FILE), false);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            
            bufferedWriter.write("ItemName,Price,Restaurant\n");
            
            for (Map.Entry<String, Double> entry : restaurant.getMenu().entrySet()) {
                String line = entry.getKey() + "," + entry.getValue() + "," + 
                             restaurant.getRestaurantName();
                bufferedWriter.write(line + "\n");
            }
            bufferedWriter.flush();
            
            System.out.println("✓ Menu saved for " + restaurant.getRestaurantName() + 
                             " (" + restaurant.getMenu().size() + " items)");
        } catch (IOException e) {
            System.err.println("Error saving menu: " + e.getMessage());
        }
    }

    /**
     * Load menu from file
     */
    public static Map<String, Double> loadMenu() {
        Map<String, Double> menu = new HashMap<>();
        File file = new File(getFilePath(MENU_FILE));
        
        if (!file.exists()) {
            System.out.println("No menu file found.");
            return menu;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isHeader = true;
            
            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                
                String[] data = line.split(",");
                if (data.length >= 2) {
                    try {
                        menu.put(data[0], Double.parseDouble(data[1]));
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing price: " + e.getMessage());
                    }
                }
            }
            
            System.out.println("✓ Loaded " + menu.size() + " menu items from file");
        } catch (IOException e) {
            System.err.println("Error loading menu: " + e.getMessage());
        }
        
        return menu;
    }

    /**
     * Get the full file path for a given filename
     */
    private static String getFilePath(String filename) {
        return DATA_DIR + File.separator + filename;
    }

    /**
     * Clear all data files (for testing purposes)
     */
    public static void clearAllData() {
        clearFile(CUSTOMERS_FILE);
        clearFile(ORDERS_FILE);
        clearFile(DELIVERIES_FILE);
        clearFile(MENU_FILE);
    }

    /**
     * Clear a specific data file
     */
    private static void clearFile(String filename) {
        File file = new File(getFilePath(filename));
        if (file.exists() && file.delete()) {
            System.out.println("✓ Cleared: " + filename);
        }
    }

    /**
     * Display all saved data (for debugging)
     */
    public static void displayAllData() {
        System.out.println("\n=== SAVED DATA SUMMARY ===");
        
        System.out.println("\n--- Customers ---");
        List<Customer> customers = loadCustomers();
        for (Customer c : customers) {
            System.out.println("  " + c.getName() + " (" + c.getEmail() + ")");
        }
        
        System.out.println("\n--- Orders ---");
        List<Order> orders = loadOrders();
        for (Order o : orders) {
            System.out.println("  " + o);
        }
        
        System.out.println("\n--- Deliveries ---");
        List<Map<String, String>> deliveries = loadDeliveries();
        for (Map<String, String> d : deliveries) {
            System.out.println("  Location: " + d.get("location") + ", Status: " + d.get("status"));
        }
        
        System.out.println("\n--- Menu ---");
        Map<String, Double> menu = loadMenu();
        for (Map.Entry<String, Double> m : menu.entrySet()) {
            System.out.println("  " + m.getKey() + ": $" + m.getValue());
        }
    }
}
