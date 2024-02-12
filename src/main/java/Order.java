import java.util.List;

public record Order(
        String id,
        List<java.util.Optional<Product>> products,
        OrderStatus orderStatus
        ) { }