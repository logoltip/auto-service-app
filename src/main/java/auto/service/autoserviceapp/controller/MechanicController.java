package auto.service.autoserviceapp.controller;

import auto.service.autoserviceapp.dto.request.MechanicRequestDto;
import auto.service.autoserviceapp.dto.response.MechanicResponseDto;
import auto.service.autoserviceapp.dto.response.PaymentResponseDto;
import auto.service.autoserviceapp.dto.response.WorkResponseDto;
import auto.service.autoserviceapp.mapper.RequestDtoMapper;
import auto.service.autoserviceapp.mapper.ResponseDtoMapper;
import auto.service.autoserviceapp.model.Mechanic;
import auto.service.autoserviceapp.model.Work;
import auto.service.autoserviceapp.service.MechanicService;
import auto.service.autoserviceapp.service.WorkService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mechanics")
@AllArgsConstructor
public class MechanicController {
    private final MechanicService mechanicService;
    private final WorkService workService;
    private final RequestDtoMapper<MechanicRequestDto, Mechanic> requestDtoMapper;
    private final ResponseDtoMapper<MechanicResponseDto, Mechanic> responseDtoMapper;
    private final ResponseDtoMapper<WorkResponseDto, Work> workResponseDtoMapper;
    private final ResponseDtoMapper<PaymentResponseDto, BigDecimal> paymentResponseDtoMapper;

    @PostMapping
    @ApiOperation(value = "Create a new mechanic")
    public MechanicResponseDto save(
            @RequestBody @ApiParam(value = "Mechanic id") MechanicRequestDto mechanicRequestDto
    ) {
        Mechanic mechanic = mechanicService.save(requestDtoMapper.mapToModel(mechanicRequestDto));
        return responseDtoMapper.mapToDto(mechanic);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a mechanic by id")
    public MechanicResponseDto update(
            @PathVariable @ApiParam(value = "Mechanic id") Long id,
            @RequestBody @ApiParam(value = "Mechanic param") MechanicRequestDto mechanicRequestDto
    ) {
        Mechanic mechanic = requestDtoMapper.mapToModel(mechanicRequestDto);
        mechanic.setId(id);
        return responseDtoMapper.mapToDto(mechanicService.save(mechanic));
    }

    @GetMapping("/{id}/works")
    @ApiOperation(value = "Get a list of completed works by mechanic")
    public List<WorkResponseDto> getWorkList(
            @PathVariable @ApiParam(value = "Mechanic id") Long id
    ) {
        Mechanic mechanic = mechanicService.findById(id);
        List<Work> workList = workService.findAllById(mechanic.getCompletedWorks().stream()
                .map(Work::getId)
                .collect(Collectors.toList()));
        return workList.stream()
                .map(workResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/salary")
    @ApiOperation(value = "Get mechanic salary")
    public PaymentResponseDto getMechanicSalary(
            @PathVariable @ApiParam(value = "Mechanic id") Long id
    ) {
        BigDecimal mechanicSalary = mechanicService.getMechanicSalary(id);
        return paymentResponseDtoMapper.mapToDto(mechanicSalary);
    }
}
