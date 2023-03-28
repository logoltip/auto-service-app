package auto.service.autoserviceapp.service;

import auto.service.autoserviceapp.model.Car;
import java.util.List;

public interface CarService {
    Car save(Car car);

    Car findById(Long id);

    List<Car> findAllByIds(List<Long> carIds);
}
