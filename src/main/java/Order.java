import lombok.With;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

public record Order(
        String id,
        Map<Optional<Product>,Double> products,
        @With
        OrderStatus orderStatus,
        Instant orderTimestamp
        ) { }