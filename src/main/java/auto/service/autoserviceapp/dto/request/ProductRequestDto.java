package auto.service.autoserviceapp.dto.request;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductRequestDto {
    private String name;
    private BigDecimal price;
}
