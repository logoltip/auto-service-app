package auto.service.autoserviceapp.service;

import auto.service.autoserviceapp.model.Order;
import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    Order save(Order order);

    Order findById(Long id);

    List<Order> findAllByIds(List<Long> orderIds);

    Order addProductsToOrder(Long orderId, List<Long> productIds);

    Order addWorksToOrder(Long orderId, List<Long> workIds);

    Order updateOrderStatus(Long orderId, Order.OrderStatus orderStatus);

    BigDecimal calculateTotalCost(Order order);
}
