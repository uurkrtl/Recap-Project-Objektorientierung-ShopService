import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ShopService {
    private ProductRepo productRepo = new ProductRepo();
    private OrderRepo orderRepo = new OrderMapRepo();

    public Order addOrder(List<String> productIds) {
        List<Optional<Product>> products = new ArrayList<>();
        for (String productId : productIds) {
            Optional<Product> productToOrder = productRepo.getProductById(productId);
            try {
                if (productToOrder.isEmpty()) {
                    throw new Exception("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            /*
            if (productToOrder == null) {
                System.out.println("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
                return null;
            }
             */

            products.add(productToOrder);
        }

        Order newOrder = new Order(UUID.randomUUID().toString(), products, OrderStatus.PROCESSING);

        return orderRepo.addOrder(newOrder);
    }

    public Order getOrderById(String orderId) {
        return orderRepo.getOrderById(orderId);
    }
}
