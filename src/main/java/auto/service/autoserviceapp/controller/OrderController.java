package auto.service.autoserviceapp.controller;

import auto.service.autoserviceapp.dto.request.OrderRequestDto;
import auto.service.autoserviceapp.dto.response.OrderResponseDto;
import auto.service.autoserviceapp.dto.response.PaymentResponseDto;
import auto.service.autoserviceapp.mapper.RequestDtoMapper;
import auto.service.autoserviceapp.mapper.ResponseDtoMapper;
import auto.service.autoserviceapp.model.Order;
import auto.service.autoserviceapp.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final ResponseDtoMapper<OrderResponseDto, Order> responseDtoMapper;
    private final RequestDtoMapper<OrderRequestDto, Order> requestDtoMapper;
    private final ResponseDtoMapper<PaymentResponseDto, BigDecimal> paymentResponseDtoMapper;

    @PostMapping
    @ApiOperation(value = "Create a new order")
    public OrderResponseDto save(
            @RequestBody @Validated @ApiParam(value = "Order parameters")
            OrderRequestDto orderRequestDto
    ) {
        Order order = requestDtoMapper.mapToModel(orderRequestDto);
        order.setOrderDate(LocalDate.now());
        order.setOrderStatus(Order.OrderStatus.ACCEPTED);
        return responseDtoMapper.mapToDto(orderService.save(order));
    }

    @PostMapping("/{id}/products")
    @ApiOperation(value = "Add products to order by list id")
    public OrderResponseDto addProductsToOrder(
            @PathVariable @ApiParam(value = "Order id") Long id,
            @RequestBody @ApiParam(value = "List of product id's") List<Long> productIds
    ) {
        Order order = orderService.addProductsToOrder(id, productIds);
        return responseDtoMapper.mapToDto(orderService.save(order));
    }

    @PostMapping("/{id}/works")
    @ApiOperation(value = "Add works to order by list id")
    public OrderResponseDto addWorksToOrder(
            @PathVariable @ApiParam(value = "Order id") Long id,
            @RequestBody @ApiParam(value = "List of work id's") List<Long> workIds
    ) {
        Order order = orderService.addWorksToOrder(id, workIds);
        return responseDtoMapper.mapToDto(orderService.save(order));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update order by id")
    public OrderResponseDto update(
            @PathVariable @ApiParam(value = "Order id") Long id,
            @RequestBody @Validated @ApiParam(value = "Order parameters")
            OrderRequestDto orderRequestDto
    ) {
        Order order = requestDtoMapper.mapToModel(orderRequestDto);
        order.setId(id);
        return responseDtoMapper.mapToDto(orderService.save(order));
    }

    @PutMapping("/{id}/status")
    @ApiOperation(value = "Update order status by order id")
    public OrderResponseDto updateOrderStatus(
            @PathVariable @ApiParam(value = "Order id") Long id,
            @RequestBody @ApiParam(value = "Order status") Order.OrderStatus orderStatus
    ) {
        Order order = orderService.updateOrderStatus(id, orderStatus);
        return responseDtoMapper.mapToDto(orderService.save(order));
    }

    @GetMapping("/{id}/cost")
    @ApiOperation(value = "Get a total cost of order")
    public PaymentResponseDto getTotalCost(
            @PathVariable @ApiParam(value = "Order id") Long id
    ) {
        Order order = orderService.findById(id);
        BigDecimal totalCost = orderService.calculateTotalCost(order);
        order.setTotalCost(totalCost);
        orderService.save(order);
        return paymentResponseDtoMapper.mapToDto(totalCost);
    }
}
