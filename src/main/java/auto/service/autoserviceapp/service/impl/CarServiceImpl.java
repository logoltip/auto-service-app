package auto.service.autoserviceapp.service.impl;

import auto.service.autoserviceapp.model.Car;
import auto.service.autoserviceapp.repository.CarRepository;
import auto.service.autoserviceapp.service.CarService;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car findById(Long id) {
        return carRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't find car by id: " + id));
    }

    @Override
    public List<Car> findAllById(List<Long> carIds) {
        return carRepository.findAllById(carIds);
    }
}
