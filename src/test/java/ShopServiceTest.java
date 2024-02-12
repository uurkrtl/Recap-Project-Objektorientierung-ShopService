import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    @Test
    void addOrderTest() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");

        //WHEN
        Order actual = shopService.addOrder(productsIds);

        //THEN
        Order expected = new Order("-1", List.of(Optional.of(new Product("1", "Apfel"))), OrderStatus.PROCESSING, Instant.now());
        assertEquals(expected.products(), actual.products());
        assertNotNull(expected.id());
    }

    @Test
    void addOrderTest_whenInvalidProductId_thrownException() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1", "2");

        //WHEN
        Order actual = shopService.addOrder(productsIds);

        //THEN
        assertThrows(Exception.class, () -> {
            throw new Exception("Product mit der Id: " + "2" + " konnte nicht bestellt werden!");
        });
    }

    @Test
    void getOrderByIdTest() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");
        Order expected = shopService.addOrder(productsIds);

        //WHEN
        Order actual = shopService.getOrderById(expected.id());

        //THEN
        assertEquals(expected, actual);
    }

    @Test
    void getOrderByIdTest_whenInvalidOrderId_expectNull() {
        //GIVEN
        ShopService shopService = new ShopService();

        //WHEN
        Order actual = shopService.getOrderById("invalidId");

        //THEN
        assertNull(actual);
    }

    @Test
    void updateOrderTest() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");
        Order order = shopService.addOrder(productsIds);

        //WHEN
        Order actual = shopService.updateOrder(order.id(), OrderStatus.COMPLETED);

        //THEN
        Order expected = new Order(order.id(), order.products(), OrderStatus.COMPLETED, Instant.now());
        assertEquals(expected, actual);
    }
}
