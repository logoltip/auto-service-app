package auto.service.autoserviceapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OwnerRequestDto {
    @NotBlank
    private String fullName;
    @NotBlank
    private String phoneNumber;
    private List<Long> carIds;
}
