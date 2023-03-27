package auto.service.autoserviceapp.dto.request;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MechanicRequestDto {
    private String fullName;
    private List<Long> completedWorkIds;
}
