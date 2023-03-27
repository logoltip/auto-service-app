package auto.service.autoserviceapp.dto.request;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OwnerRequestDto {
    private String fullName;
    private String phoneNumber;
    private List<Long> carIds;
}
