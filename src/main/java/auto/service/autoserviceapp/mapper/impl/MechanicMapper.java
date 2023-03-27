package auto.service.autoserviceapp.mapper.impl;

import auto.service.autoserviceapp.dto.request.MechanicRequestDto;
import auto.service.autoserviceapp.dto.response.MechanicResponseDto;
import auto.service.autoserviceapp.mapper.RequestDtoMapper;
import auto.service.autoserviceapp.mapper.ResponseDtoMapper;
import auto.service.autoserviceapp.model.Mechanic;
import auto.service.autoserviceapp.model.Work;
import auto.service.autoserviceapp.service.WorkService;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MechanicMapper implements RequestDtoMapper<MechanicRequestDto, Mechanic>,
        ResponseDtoMapper<MechanicResponseDto, Mechanic> {
    private final WorkService workService;

    @Override
    public Mechanic mapToModel(MechanicRequestDto dto) {
        Mechanic mechanic = new Mechanic();
        mechanic.setFullName(dto.getFullName());
        mechanic.setCompletedWorks(workService.findAllById(dto.getCompletedWorkIds()));
        return mechanic;
    }

    @Override
    public MechanicResponseDto mapToDto(Mechanic mechanic) {
        MechanicResponseDto mechanicResponseDto = new MechanicResponseDto();
        mechanicResponseDto.setId(mechanic.getId());
        mechanicResponseDto.setFullName(mechanic.getFullName());
        mechanicResponseDto.setCompletedWorkIds(mechanic.getCompletedWorks().stream()
                .map(Work::getId)
                .collect(Collectors.toList()));
        return mechanicResponseDto;
    }
}
