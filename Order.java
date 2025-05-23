public class Order {
    private int id;
    private int itemId;
    private int quantity;
    private String itemName;
    private double totalPrice;

    // Constructors, getters, setters
    public Order(int itemId, int quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public int getId() { return id; }
    public int getItemId() { return itemId; }
    public int getQuantity() { return quantity; }
    public void setId(int id) { this.id = id; }

    public void setItemName(String itemName) { this.itemName = itemName; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public String toString() {
        return "Order ID: " + id + ", Item: " + itemName + ", Quantity: " + quantity + ", Total: ₹" + totalPrice;
    }
}
