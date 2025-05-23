import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAO {

    private MenuDAO menuDAO = new MenuDAO();

    public void placeOrder(Order order) throws SQLException {
        String sql = "INSERT INTO orders (item_id, quantity) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, order.getItemId());
            ps.setInt(2, order.getQuantity());
            ps.executeUpdate();
        }
    }

    public List<Order> getAllOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT o.id, o.item_id, o.quantity, m.name, m.price " +
                     "FROM orders o JOIN menu m ON o.item_id = m.id";

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Order order = new Order(rs.getInt("item_id"), rs.getInt("quantity"));
                order.setId(rs.getInt("id"));
                order.setItemName(rs.getString("name"));
                double price = rs.getDouble("price") * rs.getInt("quantity");
                order.setTotalPrice(price);
                orders.add(order);
            }
        }
        return orders;
    }

    public boolean deleteOrder(int id) throws SQLException {
        String sql = "DELETE FROM orders WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
