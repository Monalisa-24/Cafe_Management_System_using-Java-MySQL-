import java.util.*;

public class CafeApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CafeService service = new CafeService();

        while (true) {
            System.out.println("\n== Cafe Management System ==");
            System.out.println("1. Add Menu Item");
            System.out.println("2. Show Menu");
            System.out.println("3. Place Order");
            System.out.println("4. Show Orders");
            System.out.println("5. Cancel Order");
            System.out.println("6. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();
            
            try {
                switch (choice) {
                    case 1:
                        System.out.print("Item name: ");
                        String name = sc.nextLine();
                        System.out.print("Price: ");
                        double price = sc.nextDouble();
                        service.addMenuItem(new MenuItem(name, price));
                        System.out.println("✅ Item added.");
                        break;

                    case 2:
                        List<MenuItem> menu = service.getMenu();
                        System.out.println("== Menu ==");
                        for (MenuItem item : menu) {
                            System.out.println(item);
                        }
                        break;

                    case 3:
                        System.out.print("Enter item ID: ");
                        int id = sc.nextInt();
                        System.out.print("Quantity: ");
                        int qty = sc.nextInt();
                        service.placeOrder(new Order(id, qty));
                        System.out.println("✅ Order placed.");
                        break;

                    case 4:
                        List<Order> orders = service.getAllOrders();
                        if (orders.isEmpty()) {
                            System.out.println("No orders till now.");
                        } else {
                            for (Order o : orders) {
                                System.out.println(o);
                            }
                        }
                        break;

                    case 5:
                        System.out.print("Enter Order ID to cancel: ");
                        int oid = sc.nextInt();
                        if (service.cancelOrder(oid))
                            System.out.println("✅ Order canceled.");
                        else
                            System.out.println("❌ Order not found.");
                        break;

                    case 6:
                        System.out.println("Bye!");
                        return;

                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("⚠️ Error: " + e.getMessage());
            }
        }
    }
}
