package auto.service.autoserviceapp.controller;

import auto.service.autoserviceapp.dto.request.WorkRequestDto;
import auto.service.autoserviceapp.dto.response.WorkResponseDto;
import auto.service.autoserviceapp.mapper.RequestDtoMapper;
import auto.service.autoserviceapp.mapper.ResponseDtoMapper;
import auto.service.autoserviceapp.model.Work;
import auto.service.autoserviceapp.service.WorkService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/works")
@AllArgsConstructor
public class WorkController {
    private final WorkService workService;
    private final RequestDtoMapper<WorkRequestDto, Work> requestDtoMapper;
    private final ResponseDtoMapper<WorkResponseDto, Work> responseDtoMapper;

    @PostMapping
    @ApiOperation(value = "Create a new work")
    public WorkResponseDto create(
            @RequestBody @ApiParam(value = "Work id") WorkRequestDto workRequestDto
    ) {
        Work work = workService.save(requestDtoMapper.mapToModel(workRequestDto));
        return responseDtoMapper.mapToDto(work);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a work by id")
    public WorkResponseDto update(
            @PathVariable @ApiParam(value = "Work id") Long id,
            @RequestBody @ApiParam(value = "Work parameters") WorkRequestDto workRequestDto
    ) {
        Work work = requestDtoMapper.mapToModel(workRequestDto);
        work.setId(id);
        return responseDtoMapper.mapToDto(workService.save(work));
    }

    @PutMapping("/{id}/status")
    @ApiOperation(value = "Update paid status by id")
    public WorkResponseDto updatePaidStatus(
            @PathVariable @ApiParam(value = "Work id") Long id,
            @RequestBody @ApiParam(value = "Work status") Work.PaidStatus paidStatus
    ) {
        Work work = workService.updatePaidStatus(id, paidStatus);
        return responseDtoMapper.mapToDto(workService.save(work));
    }
}
