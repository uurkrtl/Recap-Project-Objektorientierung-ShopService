import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderListRepo implements OrderRepo{
    private List<Order> orders = new ArrayList<>();

    public List<Order> getOrders() {
        return orders;
    }

    public Order getOrderById(String id) {
        for (Order order : orders) {
            if (order.id().equals(id)) {
                return order;
            }
        }
        return null;
    }

    public Order addOrder(Order newOrder) {
        orders.add(newOrder);
        return newOrder;
    }

    public void removeOrder(String id) {
        for (Order order : orders) {
            if (order.id().equals(id)) {
                orders.remove(order);
                return;
            }
        }
    }

    @Override
    public Order updateOrder(String id, OrderStatus orderStatus) {
        Order updatedOrder = this.getOrderById(id).withOrderStatus(orderStatus);
        this.removeOrder(id);
        this.addOrder(updatedOrder);
        return updatedOrder;
    }

    @Override
    public Map<OrderStatus, Order> getOldestOrderPerStatus() {
        Map<OrderStatus,Order> result = new HashMap<>();
        for (Order order : orders) {
            if (!result.containsKey(order.orderStatus()) || result.get(order.orderStatus()).orderTimestamp().isAfter(order.orderTimestamp())) {
                result.put(order.orderStatus(), order);
            }
        }
        return result;
    }
}
