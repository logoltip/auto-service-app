package auto.service.autoserviceapp.service.impl;

import auto.service.autoserviceapp.model.Order;
import auto.service.autoserviceapp.model.Product;
import auto.service.autoserviceapp.model.Work;
import auto.service.autoserviceapp.repository.OrderRepository;
import auto.service.autoserviceapp.service.OrderService;
import auto.service.autoserviceapp.service.ProductService;
import auto.service.autoserviceapp.service.WorkService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private static final int DISCOUNT_ON_THE_PRODUCT = 1;
    private static final int DISCOUNT_ON_THE_WORK = 2;
    private static final BigDecimal PRICE_OF_DIAGNOSTICS = new BigDecimal("500");
    private static final int ONLY_DIAGNOSTICS = 1;
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final WorkService workService;

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't find order by id: " + id));
    }

    @Override
    public List<Order> findAllByIds(List<Long> orderIds) {
        return orderRepository.findAllById(orderIds);
    }

    @Override
    public Order addProductsToOrder(
            Long orderId,
            List<Long> productIds
    ) {
        Order order = findById(orderId);
        List<Product> products = productService.findAllByIds(productIds);
        order.getProducts().addAll(products);
        return order;
    }

    @Override
    public Order addWorksToOrder(
            Long orderId,
            List<Long> workIds
    ) {
        Order order = findById(orderId);
        List<Work> works = workService.findAllByIds(workIds);
        order.getWorks().addAll(works);
        return order;
    }

    @Override
    public Order updateOrderStatus(
            Long orderId,
            Order.OrderStatus orderStatus
    ) {
        Order order = findById(orderId);
        if (orderStatus.equals(Order.OrderStatus.COMPLETED_SUCCESSFULLY)
                || orderStatus.equals(Order.OrderStatus.COMPLETED_UNSUCCESSFULLY)) {
            order.setCompletionDate(LocalDate.now());
        }
        order.setOrderStatus(orderStatus);
        return order;
    }

    @Override
    public BigDecimal calculateTotalCost(Order order) {
        int countOfOwnerOrders = order.getCar().getOwner().getOrders().size();
        BigDecimal worksPrice = order.getWorks().stream()
                .map(Work::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        if (order.getWorks().size() > ONLY_DIAGNOSTICS) {
            worksPrice = worksPrice.subtract(PRICE_OF_DIAGNOSTICS);
        }
        BigDecimal productsPrice = order.getProducts().stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal workPriceAfterDiscount =
                getDiscount(DISCOUNT_ON_THE_WORK, countOfOwnerOrders, worksPrice);
        BigDecimal productPriceAfterDiscount =
                getDiscount(DISCOUNT_ON_THE_PRODUCT, countOfOwnerOrders, productsPrice);
        return workPriceAfterDiscount.add(productPriceAfterDiscount);
    }

    private BigDecimal getDiscount(
            int discount,
            int countOfOwnerOrders,
            BigDecimal price
    ) {
        int discountPercentage = (discount * countOfOwnerOrders) / 100;
        return price.subtract(price.multiply(new BigDecimal(discountPercentage)));
    }
}
