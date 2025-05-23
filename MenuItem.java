public class MenuItem {
    private int id;
    private String name;
    private double price;

    public MenuItem() {}
    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public double getPrice() { return price; }

    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return id + ". " + name + " - ₹" + price;
    }
}
