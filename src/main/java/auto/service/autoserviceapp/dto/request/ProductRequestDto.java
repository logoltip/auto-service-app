package auto.service.autoserviceapp.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductRequestDto {
    @NotBlank
    private String name;
    @Min(1)
    private BigDecimal price;
}
