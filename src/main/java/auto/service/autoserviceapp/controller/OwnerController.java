package auto.service.autoserviceapp.controller;

import auto.service.autoserviceapp.dto.request.OwnerRequestDto;
import auto.service.autoserviceapp.dto.response.OrderResponseDto;
import auto.service.autoserviceapp.dto.response.OwnerResponseDto;
import auto.service.autoserviceapp.mapper.RequestDtoMapper;
import auto.service.autoserviceapp.mapper.ResponseDtoMapper;
import auto.service.autoserviceapp.model.Order;
import auto.service.autoserviceapp.model.Owner;
import auto.service.autoserviceapp.service.OwnerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import java.util.stream.Collectors;
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
@RequestMapping("/owners")
@AllArgsConstructor
public class OwnerController {
    private final OwnerService ownerService;
    private final RequestDtoMapper<OwnerRequestDto, Owner> requestDtoMapper;
    private final ResponseDtoMapper<OwnerResponseDto, Owner> responseDtoMapper;
    private final ResponseDtoMapper<OrderResponseDto, Order> orderResponseDtoMapper;

    @PostMapping
    @ApiOperation(value = "Create a new owner")
    public OwnerResponseDto create(
            @RequestBody
            @Validated
            @ApiParam(value = "Owner parameters") OwnerRequestDto ownerRequestDto
    ) {
        Owner owner = requestDtoMapper.mapToModel(ownerRequestDto);
        return responseDtoMapper.mapToDto(ownerService.save(owner));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a owner by id")
    public OwnerResponseDto update(
            @PathVariable @ApiParam(value = "Owner id") Long id,
            @RequestBody @Validated @ApiParam(value = "Owner parameters")
            OwnerRequestDto ownerRequestDto
    ) {
        Owner owner = requestDtoMapper.mapToModel(ownerRequestDto);
        owner.setId(id);
        return responseDtoMapper.mapToDto(ownerService.save(owner));
    }

    @GetMapping("/{id}/orders")
    @ApiOperation(value = "Get all orders by owner id")
    public List<OrderResponseDto> getOrdersByOwnerId(
            @PathVariable @ApiParam(value = "Owner id") Long id
    ) {
        Owner owner = ownerService.findById(id);
        return owner.getOrders().stream()
                .map(orderResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
