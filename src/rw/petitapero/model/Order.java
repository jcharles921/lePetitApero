package rw.petitapero.model;

public class Order {
    private int id;
    private int coffeeId;
    private int userId;
    private int quantity;
    private String status;

    public Order() {
    }

    public Order(int coffeeId, int userId, int quantity, String status) {
        this.coffeeId = coffeeId;
        this.userId = userId;
        this.quantity = quantity;
        this.status = status;
    }

    public Order(int id, int coffeeId, int userId, int quantity, String status) {
        this.id = id;
        this.coffeeId = coffeeId;
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
//                ", userId=" + userId +
//                ", quantity=" + quantity +
//                ", status='" + status + '\'' +
//                '}';
//    }
}