package auto.service.autoserviceapp.dto.request;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkRequestDto {
    private Long orderId;
    private Long mechanicId;
    private BigDecimal price;
}
