import java.util.List;

public record Order(
        String id,
        List<Product> products,
        OrderStatus status
) {

  /*  public OrderStatus status() {
        return status;
    }*/
}
