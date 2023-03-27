package auto.service.autoserviceapp.service;

import auto.service.autoserviceapp.model.Order;
import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    Order save(Order order);

    Order findById(Long id);

    List<Order> findAllById(List<Long> orderIds);

    Order addProductsToOrder(Long orderId, List<Long> productId);

    Order addWorksToOrder(Long orderId, List<Long> productId);

    Order updateOrderStatus(Long orderId, Order.OrderStatus orderStatus);

    BigDecimal calculateTotalCost(Order order);
}
