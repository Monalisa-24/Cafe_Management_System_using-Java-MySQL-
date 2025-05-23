import java.sql.*;
import java.util.*;

public class MenuDAO {
    public void addMenuItem(MenuItem item) throws SQLException {
        String sql = "INSERT INTO menu (name, price) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, item.getName());
            ps.setDouble(2, item.getPrice());
            ps.executeUpdate();
        }
    }

    public List<MenuItem> getAllMenuItems() throws SQLException {
        List<MenuItem> menu = new ArrayList<>();
        String sql = "SELECT * FROM menu";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                MenuItem item = new MenuItem();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setPrice(rs.getDouble("price"));
                menu.add(item);
            }
        }
        return menu;
    }

    public MenuItem getItemById(int id) throws SQLException {
        String sql = "SELECT * FROM menu WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new MenuItem(rs.getString("name"), rs.getDouble("price"));
            }
        }
        return null;
    }
}
