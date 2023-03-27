package auto.service.autoserviceapp.dto.request;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDto {
    private String problemDescription;
    private Long carId;
    private List<Long> workIds;
    private List<Long> productIds;
}
