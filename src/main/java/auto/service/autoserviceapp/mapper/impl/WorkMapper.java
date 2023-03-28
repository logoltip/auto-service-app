package auto.service.autoserviceapp.mapper.impl;

import auto.service.autoserviceapp.dto.request.WorkRequestDto;
import auto.service.autoserviceapp.dto.response.WorkResponseDto;
import auto.service.autoserviceapp.mapper.RequestDtoMapper;
import auto.service.autoserviceapp.mapper.ResponseDtoMapper;
import auto.service.autoserviceapp.model.Work;
import auto.service.autoserviceapp.service.MechanicService;
import auto.service.autoserviceapp.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class WorkMapper implements RequestDtoMapper<WorkRequestDto, Work>,
        ResponseDtoMapper<WorkResponseDto, Work> {
    private final OrderService orderService;
    private final MechanicService mechanicService;

    @Override
    public Work mapToModel(WorkRequestDto dto) {
        Work work = new Work();
        work.setPrice(dto.getPrice());
        work.setOrder(orderService.findById(dto.getOrderId()));
        work.setMechanic(mechanicService.findById(dto.getMechanicId()));
        return work;
    }

    @Override
    public WorkResponseDto mapToDto(Work work) {
        WorkResponseDto workResponseDto = new WorkResponseDto();
        workResponseDto.setId(work.getId());
        workResponseDto.setPrice(work.getPrice());
        workResponseDto.setPaidStatus(work.getPaidStatus());
        workResponseDto.setOrderId(work.getOrder().getId());
        workResponseDto.setMechanicId(work.getMechanic().getId());
        return workResponseDto;
    }
}
