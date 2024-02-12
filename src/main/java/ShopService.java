import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class ShopService {
    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;
    private final IdService idService;

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
            products.add(productToOrder);
        }

        Order newOrder = new Order(idService.generateId(), products, OrderStatus.PROCESSING, Instant.now());

        return orderRepo.addOrder(newOrder);
    }

    public Order getOrderById(String orderId) {
        return orderRepo.getOrderById(orderId);
    }

    public Order updateOrder(String orderId, OrderStatus orderStatus) {
        return orderRepo.updateOrder(orderId, orderStatus);
    }
}
