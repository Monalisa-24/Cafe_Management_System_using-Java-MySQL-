import java.sql.SQLException;
import java.util.*;

public class CafeService {
    private MenuDAO menuDAO = new MenuDAO();
    private OrdersDAO orderDAO = new OrdersDAO();

    public void addMenuItem(MenuItem item) throws SQLException {
        menuDAO.addMenuItem(item);
    }

    public List<MenuItem> getMenu() throws SQLException {
        return menuDAO.getAllMenuItems();
    }

    public void placeOrder(Order order) throws SQLException {
        orderDAO.placeOrder(order);
    }

    public List<Order> getAllOrders() throws SQLException {
        return orderDAO.getAllOrders();
    }

    public boolean cancelOrder(int id) throws SQLException {
        return orderDAO.deleteOrder(id);
    }
}
