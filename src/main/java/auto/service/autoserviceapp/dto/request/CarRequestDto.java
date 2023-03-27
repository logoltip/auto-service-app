package auto.service.autoserviceapp.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarRequestDto {
    private String manufacturer;
    private String model;
    private Integer year;
    private String licensePlateNumber;
    private Long ownerId;
}
