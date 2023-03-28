package auto.service.autoserviceapp.mapper.impl;

import auto.service.autoserviceapp.dto.request.OwnerRequestDto;
import auto.service.autoserviceapp.dto.response.OwnerResponseDto;
import auto.service.autoserviceapp.mapper.RequestDtoMapper;
import auto.service.autoserviceapp.mapper.ResponseDtoMapper;
import auto.service.autoserviceapp.model.Car;
import auto.service.autoserviceapp.model.Order;
import auto.service.autoserviceapp.model.Owner;
import auto.service.autoserviceapp.service.CarService;
import auto.service.autoserviceapp.service.OrderService;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OwnerMapper implements RequestDtoMapper<OwnerRequestDto, Owner>,
        ResponseDtoMapper<OwnerResponseDto, Owner> {
    private final CarService carService;
    private final OrderService orderService;

    @Override
    public Owner mapToModel(OwnerRequestDto dto) {
        Owner owner = new Owner();
        owner.setFullName(dto.getFullName());
        owner.setPhoneNumber(dto.getPhoneNumber());
        owner.setCars(carService.findAllByIds(dto.getCarIds()));
        owner.setOrders(orderService.findAllByIds(dto.getCarIds()));
        return owner;
    }

    @Override
    public OwnerResponseDto mapToDto(Owner owner) {
        OwnerResponseDto ownerResponseDto = new OwnerResponseDto();
        ownerResponseDto.setId(owner.getId());
        ownerResponseDto.setFullName(owner.getFullName());
        ownerResponseDto.setPhoneNumber(owner.getPhoneNumber());
        ownerResponseDto.setCarIds(owner.getCars().stream()
                .map(Car::getId)
                .collect(Collectors.toList()));
        ownerResponseDto.setOrderIds(owner.getOrders().stream()
                .map(Order::getId)
                .collect(Collectors.toList()));
        return ownerResponseDto;
    }
}
