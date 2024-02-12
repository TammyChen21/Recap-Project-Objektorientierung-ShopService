import lombok.Data;
import lombok.With;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Data

public class ShopService {
    private ProductRepo productRepo = new ProductRepo();
    private OrderRepo orderRepo = new OrderMapRepo();

    public Order addOrder(List<String> productIds) throws ProductNotFoundException{

        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Optional<Product> optionalProduct= productRepo.getProductById(productId);
            if (optionalProduct.isEmpty()) {
                throw new ProductNotFoundException("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
            }
            products.add(optionalProduct.get());
        }
        Order newOrder = new Order(UUID.randomUUID().toString(), products, OrderStatus.PROCESSING);
        return orderRepo.addOrder(newOrder);
    }

    public List<Order> getOrdersByStatus(OrderStatus status) {
        return orderRepo.getAllOrders().stream()
                .filter(order -> order.status()== status)
                .collect(Collectors.toList());
    }

    public Order updateOrder(String orderId, OrderStatus newStatus) throws OrderNotFoundException{
        Order orderToUpdate= orderRepo.getOrderById(orderId);
        if(orderToUpdate==null){
            throw new OrderNotFoundException("Order mit der Id: " + orderId + " konnte nicht gefunden werden!");
        }
        return orderToUpdate.withStatus(newStatus);
    }
    private Order getOrderById(String orderId) {
        return orderRepo.getAllOrders().stream()
                .filter(order -> order.id().equals(orderId))
                .findFirst()
                .orElse(null);
    }
}

    /*public Order addOrder(List<String> productIds) throws ProductNotFoundException {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Optional<Product> optionalProduct= productRepo.getProductById(productId);
            if (optionalProduct.isEmpty()) {
                throw new ProductNotFoundException("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
            }
            products.add(optionalProduct.get());
        }

        Order newOrder = new Order(UUID.randomUUID().toString(), products, OrderStatus.PROCESSING);

        return orderRepo.addOrder(newOrder);
    }*/
    /*public List<Order> getOrdersByStatus(OrderStatus status) {
        return orderRepo.getAllOrders().stream()
                .filter(order -> order.status()== status)
                .collect(Collectors.toList());
    }*/

