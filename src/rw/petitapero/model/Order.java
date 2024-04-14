package rw.petitapero.model;

public class Order {
    private int id;
    private int coffeeId;
    private String coffeeName; // Add coffeeName field
    private int userId;
    private int quantity;
    private String status;

    public Order() {
    }

    public Order(int coffeeId, String coffeeName, int userId, int quantity, String status) { // Update constructor
        this.coffeeId = coffeeId;
        this.coffeeName = coffeeName;
        this.userId = userId;
        this.quantity = quantity;
        this.status = status;
    }

    public Order(int id, int coffeeId, String coffeeName, int userId, int quantity, String status) { // Update constructor
        this.id = id;
        this.coffeeId = coffeeId;
        this.coffeeName = coffeeName;
        this.userId = userId;
        this.quantity = quantity;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCoffeeId() {
        return coffeeId;
    }

    public void setCoffeeId(int coffeeId) {
        this.coffeeId = coffeeId;
    }

    public String getCoffeeName() { // Add getter for coffeeName
        return coffeeName;
    }

    public void setCoffeeName(String coffeeName) { // Add setter for coffeeName
        this.coffeeName = coffeeName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

//    @Override
//    public String toString() {
//        return "Order{" +
//                "id=" + id +
//                ", coffeeId=" + coffeeId +
//                ", coffeeName='" + coffeeName + '\'' + // Include coffeeName in toString if needed
//                ", userId=" + userId +
//                ", quantity=" + quantity +
//                ", status='" + status + '\'' +
//                '}';
//    }
}
