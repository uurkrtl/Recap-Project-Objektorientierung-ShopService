import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.*;

@RequiredArgsConstructor
public class ShopService {
    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;
    private final IdService idService;

    public Order addOrder(Map<String,Double> productIds) {
        Map<Optional<Product>,Double> products = new HashMap<>();
        for (String productId : productIds.keySet()) {
            Optional<Product> productToOrder = productRepo.getProductById(productId);
            try {
                if (productToOrder.isEmpty()) {
                    throw new Exception("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
                } else if (productToOrder.get().stock() < productIds.get(productId)){
                    throw new Exception("Nicht genug Vorrat für das Produkt mit der Id: " + productId);
                } else {
                    productRepo.updateProductStock(productId, productToOrder.get().stock() - productIds.get(productId));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            products.put(productToOrder, productIds.get(productId));
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

    public Map<OrderStatus, Order> getOldestOrderPerStatus() {
        return orderRepo.getOldestOrderPerStatus();
    }
}
