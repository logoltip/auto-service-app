package auto.service.autoserviceapp.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarResponseDto {
    private Long id;
    private String manufacturer;
    private String model;
    private Integer year;
    private String licensePlateNumber;
    private Long ownerId;
}
