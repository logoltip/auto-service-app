package auto.service.autoserviceapp.dto.response;

import auto.service.autoserviceapp.model.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponseDto {
    private Long id;
    private String problemDescription;
    private Long carId;
    private LocalDate orderDate;
    private List<Long> workIds;
    private List<Long> productIds;
    private Order.OrderStatus orderStatus;
    private BigDecimal totalCost;
    private LocalDate completionDate;
}
