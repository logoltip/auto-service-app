package auto.service.autoserviceapp.mapper.impl;

import auto.service.autoserviceapp.dto.request.CarRequestDto;
import auto.service.autoserviceapp.dto.response.CarResponseDto;
import auto.service.autoserviceapp.mapper.RequestDtoMapper;
import auto.service.autoserviceapp.mapper.ResponseDtoMapper;
import auto.service.autoserviceapp.model.Car;
import auto.service.autoserviceapp.service.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CarMapper implements RequestDtoMapper<CarRequestDto, Car>,
        ResponseDtoMapper<CarResponseDto, Car> {
    private final OwnerService ownerService;

    @Override
    public Car mapToModel(CarRequestDto dto) {
        Car car = new Car();
        car.setModel(dto.getModel());
        car.setYear(dto.getYear());
        car.setOwner(ownerService.findById(dto.getOwnerId()));
        car.setManufacturer(dto.getManufacturer());
        car.setLicensePlateNumber(dto.getLicensePlateNumber());
        return car;
    }

    @Override
    public CarResponseDto mapToDto(Car car) {
        CarResponseDto carResponseDto = new CarResponseDto();
        carResponseDto.setId(car.getId());
        carResponseDto.setYear(car.getYear());
        carResponseDto.setModel(car.getModel());
        carResponseDto.setManufacturer(car.getManufacturer());
        carResponseDto.setLicensePlateNumber(car.getLicensePlateNumber());
        carResponseDto.setOwnerId(car.getOwner().getId());
        return carResponseDto;
    }
}
