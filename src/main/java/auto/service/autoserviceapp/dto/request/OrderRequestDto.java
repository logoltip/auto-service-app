package auto.service.autoserviceapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDto {
    @NotBlank
    private String problemDescription;
    private Long carId;
    private List<Long> workIds;
    private List<Long> productIds;
}
