import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    @Test
    void addOrderTest() {
        //GIVEN
        ShopService shopService = new ShopService(new ProductRepo(), new OrderListRepo(), new IdService());

        //WHEN
        Order actual = shopService.addOrder(Map.of("1", 1.0));

        //THEN
        Order expected = new Order(actual.id(), Map.of(Optional.of(new Product("1", "Apfel", 10)), 1.0), OrderStatus.PROCESSING, Instant.now());
        assertEquals(expected.products(), actual.products());
        assertNotNull(expected.id());
    }

    @Test
    void addOrderTest_whenInvalidProductId_thrownException() {
        //GIVEN
        ShopService shopService = new ShopService(new ProductRepo(), new OrderListRepo(), new IdService());

        //THEN
        assertThrows(IllegalStateException.class, () -> {
            shopService.addOrder(Map.of("2", 1.0));
        });
    }

    @Test
    void addOrderTest_whenStockZero_thrownException() {
        //GIVEN
        ShopService shopService = new ShopService(new ProductRepo(), new OrderListRepo(), new IdService());

        //THEN
        assertThrows(NullPointerException.class, () -> {
            shopService.addOrder(Map.of("1", 11.0));
        });
    }

    @Test
    void getOrderByIdTest() {
        //GIVEN
        ShopService shopService = new ShopService(new ProductRepo(), new OrderListRepo(), new IdService());
        //List<String> productsIds = List.of("1");
        Order expected = shopService.addOrder(Map.of("1", 1.0));

        //WHEN
        Order actual = shopService.getOrderById(expected.id());

        //THEN
        assertEquals(expected, actual);
    }

    @Test
    void getOrderByIdTest_whenInvalidOrderId_expectNull() {
        //GIVEN
        ShopService shopService = new ShopService(new ProductRepo(), new OrderListRepo(), new IdService());

        //WHEN
        Order actual = shopService.getOrderById("invalidId");

        //THEN
        assertNull(actual);
    }

    @Test
    void updateOrderTest() {
        //GIVEN
        ShopService shopService = new ShopService(new ProductRepo(), new OrderListRepo(), new IdService());
        //List<String> productsIds = List.of("1");
        Order order = shopService.addOrder(Map.of("1", 1.0));

        //WHEN
        Order actual = shopService.updateOrder(order.id(), OrderStatus.COMPLETED);

        //THEN
        Order expected = new Order(order.id(), order.products(), OrderStatus.COMPLETED, order.orderTimestamp());
        assertEquals(expected, actual);
    }


    @Test
    void getOldestOrderPerStatusTest() {
        //GIVEN
        ShopService shopService = new ShopService(new ProductRepo(), new OrderListRepo(), new IdService());
        //List<String> productsIds = List.of("1");
        Order order1 = shopService.addOrder(Map.of("1", 1.0));
        Order order2 = shopService.addOrder(Map.of("1", 1.0));
        Order order3 = shopService.addOrder(Map.of("1", 1.0));
        Order order4 = shopService.addOrder(Map.of("1", 1.0));
        Order order5 = shopService.addOrder(Map.of("1", 1.0));
        Order order6 = shopService.addOrder(Map.of("1", 1.0));
        Order order7 = shopService.addOrder(Map.of("1", 1.0));

        shopService.updateOrder(order1.id(), OrderStatus.COMPLETED);
        shopService.updateOrder(order3.id(), OrderStatus.IN_DELIVERY);
        shopService.updateOrder(order4.id(), OrderStatus.COMPLETED);
        shopService.updateOrder(order5.id(), OrderStatus.IN_DELIVERY);
        shopService.updateOrder(order6.id(), OrderStatus.PROCESSING);
        shopService.updateOrder(order7.id(), OrderStatus.COMPLETED);

        //WHEN
        List<Order> actual = List.of(shopService.getOldestOrderPerStatus().get(OrderStatus.COMPLETED), shopService.getOldestOrderPerStatus().get(OrderStatus.PROCESSING), shopService.getOldestOrderPerStatus().get(OrderStatus.IN_DELIVERY));

        //THEN
        List<Order> expected = List.of(order1.withOrderStatus(OrderStatus.COMPLETED), order2.withOrderStatus(OrderStatus.PROCESSING), order3.withOrderStatus(OrderStatus.IN_DELIVERY));
        assertEquals(expected, actual);
    }
}