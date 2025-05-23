import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

public class CafeSwingApp extends JFrame {
    private CafeService service = new CafeService();

    private DefaultListModel<MenuItem> menuListModel = new DefaultListModel<>();
    private JList<MenuItem> menuList = new JList<>(menuListModel);

    private DefaultListModel<Order> orderListModel = new DefaultListModel<>();
    private JList<Order> orderList = new JList<>(orderListModel);

    private JTextField itemNameField = new JTextField(15);
    private JTextField itemPriceField = new JTextField(7);

    private JTextField orderQuantityField = new JTextField(5);

    public CafeSwingApp() {
        setTitle("Cafe Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        add(panel);

        // Left panel for menu management
        JPanel leftPanel = new JPanel(new BorderLayout(5, 5));
        leftPanel.setBorder(BorderFactory.createTitledBorder("Menu"));

        menuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        leftPanel.add(new JScrollPane(menuList), BorderLayout.CENTER);

        JPanel addMenuPanel = new JPanel();
        addMenuPanel.add(new JLabel("Name:"));
        addMenuPanel.add(itemNameField);
        addMenuPanel.add(new JLabel("Price:"));
        addMenuPanel.add(itemPriceField);
        JButton addMenuButton = new JButton("Add Item");
        addMenuPanel.add(addMenuButton);

        leftPanel.add(addMenuPanel, BorderLayout.SOUTH);

        // Right panel for orders
        JPanel rightPanel = new JPanel(new BorderLayout(5, 5));
        rightPanel.setBorder(BorderFactory.createTitledBorder("Orders"));

        orderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        rightPanel.add(new JScrollPane(orderList), BorderLayout.CENTER);

        JPanel orderPanel = new JPanel();
        orderPanel.add(new JLabel("Quantity:"));
        orderPanel.add(orderQuantityField);

        JButton placeOrderButton = new JButton("Place Order");
        JButton cancelOrderButton = new JButton("Cancel Selected Order");

        orderPanel.add(placeOrderButton);
        orderPanel.add(cancelOrderButton);

        rightPanel.add(orderPanel, BorderLayout.SOUTH);

        panel.add(leftPanel, BorderLayout.WEST);
        panel.add(rightPanel, BorderLayout.CENTER);

        // Load menu items on startup
        loadMenuItems();
        loadOrders();

        // Button listeners
        addMenuButton.addActionListener(e -> addMenuItem());
        placeOrderButton.addActionListener(e -> placeOrder());
        cancelOrderButton.addActionListener(e -> cancelOrder());
    }

    private void loadMenuItems() {
        try {
            List<MenuItem> menu = service.getMenu();
            menuListModel.clear();
            for (MenuItem item : menu) {
                menuListModel.addElement(item);
            }
        } catch (SQLException e) {
            showError("Failed to load menu: " + e.getMessage());
        }
    }

    private void loadOrders() {
        try {
            List<Order> orders = service.getAllOrders();
            orderListModel.clear();
            for (Order o : orders) {
                orderListModel.addElement(o);
            }
        } catch (SQLException e) {
            showError("Failed to load orders: " + e.getMessage());
        }
    }

    private void addMenuItem() {
        String name = itemNameField.getText().trim();
        String priceText = itemPriceField.getText().trim();

        if (name.isEmpty() || priceText.isEmpty()) {
            showError("Please enter both name and price.");
            return;
        }

        try {
            double price = Double.parseDouble(priceText);
            MenuItem item = new MenuItem(name, price);
            service.addMenuItem(item);
            showMessage("Menu item added.");
            itemNameField.setText("");
            itemPriceField.setText("");
            loadMenuItems();
        } catch (NumberFormatException e) {
            showError("Invalid price format.");
        } catch (SQLException e) {
            showError("Failed to add menu item: " + e.getMessage());
        }
    }

    private void placeOrder() {
        MenuItem selected = menuList.getSelectedValue();
        if (selected == null) {
            showError("Please select a menu item to order.");
            return;
        }

        String qtyText = orderQuantityField.getText().trim();
        if (qtyText.isEmpty()) {
            showError("Please enter quantity.");
            return;
        }

        try {
            int qty = Integer.parseInt(qtyText);
            if (qty <= 0) {
                showError("Quantity must be positive.");
                return;
            }
            Order order = new Order(selected.getId(), qty);
            service.placeOrder(order);
            showMessage("Order placed.");
            orderQuantityField.setText("");
            loadOrders();
        } catch (NumberFormatException e) {
            showError("Invalid quantity.");
        } catch (SQLException e) {
            showError("Failed to place order: " + e.getMessage());
        }
    }

    private void cancelOrder() {
        Order selected = orderList.getSelectedValue();
        if (selected == null) {
            showError("Please select an order to cancel.");
            return;
        }

        try {
            boolean success = service.cancelOrder(selected.getId());
            if (success) {
                showMessage("Order canceled.");
                loadOrders();
            } else {
                showError("Failed to cancel order.");
            }
        } catch (SQLException e) {
            showError("Failed to cancel order: " + e.getMessage());
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CafeSwingApp().setVisible(true);
        });
    }
}
