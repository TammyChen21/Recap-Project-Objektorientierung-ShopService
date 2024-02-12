import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    @Test
    void addOrderTest() throws ProductNotFoundException {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");

        //WHEN
        Order actual = shopService.addOrder(productsIds);

        //THEN
        Order expected = new Order("-1", List.of(new Product("1", "Apfel")), OrderStatus.PROCESSING);
        assertEquals(expected.products(), actual.products());
        assertNotNull(expected.id());
    }

    @Test
    void addOrderTest_whenInvalidProductId_expectNull() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1", "2");

        //WHEN
        Order actual = null;
try {
    actual = shopService.addOrder(productsIds);
} catch (ProductNotFoundException e) {

}

        //THEN
        assertNull(actual);
    }

    @Test
    void changeOrderStatusTest() throws ProductNotFoundException {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");
        Order order = shopService.addOrder(productsIds);

        //WHEN
        List<Order> actual = shopService.getOrdersByStatus(OrderStatus.IN_DELIVERY);

        //THEN
        Order expected = new Order(order.id(), order.products(), OrderStatus.IN_DELIVERY);
        assertEquals(expected, actual);
    }

    @Test
    void changeOrderStatusTest_whenInvalidOrderId_expectNull() {
        //GIVEN
        ShopService shopService = new ShopService();

        //WHEN
        List<Order> actual = null;
        actual = shopService.getOrdersByStatus(OrderStatus.IN_DELIVERY);
        //THEN
        assertNull(actual);
    }
}
