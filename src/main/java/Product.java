import lombok.With;

public record Product(
        String id,
        String name,
        @With
        double stock
) {
}
