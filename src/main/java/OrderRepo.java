import java.util.List;
import java.util.Map;

public interface OrderRepo {

    List<Order> getOrders();

    Order getOrderById(String id);

    Order addOrder(Order newOrder);

    void removeOrder(String id);

    Order updateOrder(String id, OrderStatus orderStatus);
    Map<OrderStatus, Order> getOldestOrderPerStatus();
}
