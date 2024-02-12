
import lombok.Data;
import lombok.With;

import java.util.List;

public record Order(
        String id,
        List<Product> products,
        OrderStatus status
) {

    public Order withStatus(OrderStatus status) {
        return new Order(this.id, this.products, this.status);
    }
}

