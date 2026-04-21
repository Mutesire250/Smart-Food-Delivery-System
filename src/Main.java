import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Customer myCustomer = null;
        Order myOrder = null;
        Delivery myDelivery = null;

        System.out.println("--- Welcome to the Smart Food Delivery System ---");
        System.out.println();

        // 1. User Registration with Robust Handling
        while (myCustomer == null) {
            try {
                System.out.print("Enter your name: ");
                String name = scanner.nextLine();
                
                System.out.print("Enter your email: ");
                String email = scanner.nextLine();

                myCustomer = new Customer(name, email);
                System.out.println("\nCustomer Profile Created Successfully:");
                myCustomer.displayInfo();
            } catch (InvalidUserException e) {
                System.out.println("Registration Error: " + e.getMessage());
                System.out.println("Please try again.\n");
            }
        }

        System.out.println();
        Restaurant myRestaurant = new Restaurant("Miribe Kitchen");

        // 2. Order Placement with Exception Handling
        while (myOrder == null) {
            try {
                System.out.print("What would you like to order? ");
                String foodItem = scanner.nextLine();

                // Simulating price and ID (could also be user input)
                myOrder = myCustomer.placeOrder(101, foodItem, 15.99);
                System.out.println("Order created: " + myOrder);
            } catch (InvalidOrderException e) {
                System.out.println("Order Error: " + e.getMessage());
                System.out.println("Please try again.\n");
            }
        }

        System.out.println();
        try {
            myRestaurant.prepareOrder(myOrder);
        } catch (IllegalArgumentException e) {
            System.out.println("Restaurant Error: " + e.getMessage());
        }

        System.out.println();
        
        // 3. Delivery with Multiple Catch Blocks
        while (myDelivery == null) {
            try {
                System.out.print("Enter delivery address: ");
                String address = scanner.nextLine();

                myDelivery = new Delivery(address, myOrder);
                myDelivery.trackDelivery();
                myDelivery.deliver();
                
                // Demonstrating multiple catch blocks for different failure scenarios
            } catch (InvalidDeliveryException e) {
                System.out.println("Delivery Error: " + e.getMessage());
                System.out.println("Please provide a valid address.\n");
            } catch (DeliverySystemException e) {
                System.out.println("System Error: A delivery system failure occurred: " + e.getMessage());
                break; // Exit loop on general system error
            } catch (Exception e) {
                System.out.println("Unexpected Error: " + e.getMessage());
                e.printStackTrace();
                break;
            }
        }

        // Bonus: Attempting to deliver again to show state validation exception
        System.out.println("\n--- State Validation Demo ---");
        try {
            if (myDelivery != null) {
                System.out.println("Attempting to deliver the same order again...");
                myDelivery.deliver();
            }
        } catch (InvalidDeliveryException e) {
            System.out.println("Caught Expected Exception: " + e.getMessage());
        }

        System.out.println();
        System.out.println("--- End of Simulation ---");
        
        scanner.close();
    }
}
