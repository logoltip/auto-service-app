package auto.service.autoserviceapp.service;

import auto.service.autoserviceapp.model.Mechanic;
import java.math.BigDecimal;

public interface MechanicService {
    Mechanic save(Mechanic mechanic);

    Mechanic findById(Long id);

    BigDecimal getSalary(Long id);
}
