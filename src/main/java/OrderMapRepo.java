import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderMapRepo implements OrderRepo{
    private Map<String, Order> orders = new HashMap<>();

    @Override
    public List<Order> getOrders() {
        return new ArrayList<>(orders.values());
    }

    @Override
    public Order getOrderById(String id) {
        return orders.get(id);
    }

    @Override
    public Order addOrder(Order newOrder) {
        orders.put(newOrder.id(), newOrder);
        return newOrder;
    }

    @Override
    public void removeOrder(String id) {
        orders.remove(id);
    }

    @Override
    public Order updateOrder(String id, OrderStatus orderStatus) {
        return this.addOrder(this.getOrderById(id).withOrderStatus(orderStatus));
    }

    @Override
    public Map<OrderStatus, Order> getOldestOrderPerStatus() {
        Map<OrderStatus,Order> result = new HashMap<>();
        for (Order order : orders.values()) {
            if (!result.containsKey(order.orderStatus()) || result.get(order.orderStatus()).orderTimestamp().isAfter(order.orderTimestamp())) {
                result.put(order.orderStatus(), order);
            }
        }
        return result;
    }
}
