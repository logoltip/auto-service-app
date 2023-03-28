package auto.service.autoserviceapp.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarRequestDto {
    @NotBlank
    private String manufacturer;
    @NotBlank
    private String model;
    @Min(1885)
    private Integer year;
    @NotBlank
    private String licensePlateNumber;
    private Long ownerId;
}
