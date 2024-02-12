import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OrderMapRepoTest {

    @Test
    void getOrders() {
        //GIVEN
        OrderMapRepo repo = new OrderMapRepo();

        Product product = new Product("1", "Apfel", 10.0);
        Order newOrder = new Order("1", Map.of(Optional.of(product), 1.0), OrderStatus.PROCESSING, Instant.now());
        repo.addOrder(newOrder);

        //WHEN
        List<Order> actual = repo.getOrders();

        //THEN
        List<Order> expected = new ArrayList<>();
        Product product1 = new Product("1", "Apfel", 10.0);
        expected.add(new Order("1", Map.of(Optional.of(product1), 1.0), OrderStatus.PROCESSING, newOrder.orderTimestamp()));

        assertEquals(actual, expected);
    }

    @Test
    void getOrderById() {
        //GIVEN
        OrderMapRepo repo = new OrderMapRepo();

        Product product = new Product("1", "Apfel", 10.0);
        Order newOrder = new Order("1", Map.of(Optional.of(product), 1.0), OrderStatus.PROCESSING, Instant.now());
        repo.addOrder(newOrder);

        //WHEN
        Order actual = repo.getOrderById("1");

        //THEN
        Product product1 = new Product("1", "Apfel", 10.0);
        Order expected = new Order("1", Map.of(Optional.of(product1), 1.0), OrderStatus.PROCESSING, newOrder.orderTimestamp());

        assertEquals(actual, expected);
    }

    @Test
    void addOrder() {
        //GIVEN
        OrderMapRepo repo = new OrderMapRepo();
        Product product = new Product("1", "Apfel", 10.0);
        Order newOrder = new Order("1", Map.of(Optional.of(product), 1.0), OrderStatus.PROCESSING, Instant.now());

        //WHEN
        Order actual = repo.addOrder(newOrder);

        //THEN
        Product product1 = new Product("1", "Apfel", 10.0);
        Order expected = new Order("1", Map.of(Optional.of(product1), 1.0), OrderStatus.PROCESSING, newOrder.orderTimestamp());
        assertEquals(actual, expected);
        assertEquals(repo.getOrderById("1"), expected);
    }

    @Test
    void removeOrder() {
        //GIVEN
        OrderMapRepo repo = new OrderMapRepo();

        //WHEN
        repo.removeOrder("1");

        //THEN
        assertNull(repo.getOrderById("1"));
    }
}
