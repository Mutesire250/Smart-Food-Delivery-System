public class Restaurant {
    private String restaurantName;

    public Restaurant(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void prepareOrder(Order order) {
        System.out.println(restaurantName + " is preparing " + order.getItemName() + ".");
        System.out.println("Processing " + order.toString());
    }
}
