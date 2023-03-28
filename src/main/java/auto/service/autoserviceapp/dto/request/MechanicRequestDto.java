package auto.service.autoserviceapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MechanicRequestDto {
    @NotBlank
    private String fullName;
}
