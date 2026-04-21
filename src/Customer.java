public class Customer extends User {

    public Customer(String name, String email) {
        super(name, email);
    }

    @Override
    public void displayInfo() {
        System.out.println("Customer Profile:");
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
    }

    public Order placeOrder(int orderId, String itemName, double price) {
        System.out.println(getName() + " is placing an order for " + itemName + ".");
        return new Order(orderId, itemName, price);
    }
}
