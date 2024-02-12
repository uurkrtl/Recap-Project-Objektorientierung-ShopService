import lombok.With;

import java.util.List;

public record Order(
        String id,
        List<java.util.Optional<Product>> products,
        @With
        OrderStatus orderStatus
        ) { }