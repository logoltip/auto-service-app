package auto.service.autoserviceapp.mapper.impl;

import auto.service.autoserviceapp.dto.request.OrderRequestDto;
import auto.service.autoserviceapp.dto.response.OrderResponseDto;
import auto.service.autoserviceapp.mapper.RequestDtoMapper;
import auto.service.autoserviceapp.mapper.ResponseDtoMapper;
import auto.service.autoserviceapp.model.Order;
import auto.service.autoserviceapp.model.Product;
import auto.service.autoserviceapp.model.Work;
import auto.service.autoserviceapp.service.CarService;
import auto.service.autoserviceapp.service.ProductService;
import auto.service.autoserviceapp.service.WorkService;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderMapper implements RequestDtoMapper<OrderRequestDto, Order>,
        ResponseDtoMapper<OrderResponseDto, Order> {
    private final CarService carService;
    private final ProductService productService;
    private final WorkService workService;

    @Override
    public Order mapToModel(OrderRequestDto dto) {
        Order order = new Order();
        order.setCar(carService.findById(dto.getCarId()));
        order.setProblemDescription(dto.getProblemDescription());
        order.setProducts(productService.findAllByIds(dto.getProductIds()));
        order.setWorks(workService.findAllByIds(dto.getWorkIds()));
        return order;
    }

    @Override
    public OrderResponseDto mapToDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setOrderDate(order.getOrderDate());
        orderResponseDto.setOrderStatus(order.getOrderStatus());
        orderResponseDto.setProblemDescription(order.getProblemDescription());
        orderResponseDto.setTotalCost(order.getTotalCost());
        orderResponseDto.setCarId(order.getCar().getId());
        orderResponseDto.setProductIds(order.getProducts().stream()
                .map(Product::getId)
                .collect(Collectors.toList()));
        orderResponseDto.setWorkIds(order.getWorks().stream()
                .map(Work::getId)
                .collect(Collectors.toList()));
        orderResponseDto.setCompletionDate(order.getCompletionDate());
        return orderResponseDto;
    }
}
