package auto.service.autoserviceapp.controller;

import auto.service.autoserviceapp.dto.request.CarRequestDto;
import auto.service.autoserviceapp.dto.response.CarResponseDto;
import auto.service.autoserviceapp.mapper.RequestDtoMapper;
import auto.service.autoserviceapp.mapper.ResponseDtoMapper;
import auto.service.autoserviceapp.model.Car;
import auto.service.autoserviceapp.service.CarService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
@AllArgsConstructor
public class CarController {
    private final CarService carService;
    private final ResponseDtoMapper<CarResponseDto, Car> responseDtoMapper;
    private final RequestDtoMapper<CarRequestDto, Car> requestDtoMapper;

    @PostMapping
    @ApiOperation(value = "Create a new car")
    public CarResponseDto create(
            @RequestBody @Validated @ApiParam(value = "Car parameters")
            CarRequestDto carRequestDto
    ) {
        Car car = requestDtoMapper.mapToModel(carRequestDto);
        return responseDtoMapper.mapToDto(carService.save(car));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a car by id")
    public CarResponseDto update(
            @PathVariable @ApiParam(value = "Car id") Long id,
            @RequestBody @Validated @ApiParam(value = "Car parameters")
            CarRequestDto carRequestDto
    ) {
        Car car = requestDtoMapper.mapToModel(carRequestDto);
        car.setId(id);
        return responseDtoMapper.mapToDto(carService.save(car));
    }
}
