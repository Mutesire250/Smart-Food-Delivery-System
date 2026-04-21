public class Main {
    public static void main(String[] args) {
        System.out.println("--- Welcome to the Smart Food Delivery System ---");
        System.out.println();

        Restaurant myRestaurant = new Restaurant("Gourmet Burger Kitchen");

        Customer myCustomer = new Customer("John Doe", "john.doe@example.com");

        myCustomer.displayInfo();
        System.out.println();

        Order myOrder = myCustomer.placeOrder(101, "Triple Cheese Burger", 15.99);
        System.out.println();

        myRestaurant.prepareOrder(myOrder);
        System.out.println();

        Delivery myDelivery = new Delivery("123 Maple Street, Springfield", myOrder);


        myDelivery.trackDelivery();

        myDelivery.deliver();

        System.out.println();
        System.out.println("--- End of Simulation ---");
    }
}
