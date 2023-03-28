package auto.service.autoserviceapp.dto.response;

import auto.service.autoserviceapp.model.Work;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkResponseDto {
    private Long id;
    private Long orderId;
    private Long mechanicId;
    private BigDecimal price;
    private Work.PaymentStatus paidStatus;
}
