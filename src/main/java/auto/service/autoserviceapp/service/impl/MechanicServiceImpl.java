package auto.service.autoserviceapp.service.impl;

import auto.service.autoserviceapp.model.Mechanic;
import auto.service.autoserviceapp.model.Work;
import auto.service.autoserviceapp.repository.MechanicRepository;
import auto.service.autoserviceapp.service.MechanicService;
import auto.service.autoserviceapp.service.WorkService;
import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MechanicServiceImpl implements MechanicService {
    private static final BigDecimal PERCENTAGE_OF_MECHANIC_SALARY = new BigDecimal("0.4");
    private final MechanicRepository mechanicRepository;
    private final WorkService workService;

    @Override
    public Mechanic save(Mechanic mechanic) {
        return mechanicRepository.save(mechanic);
    }

    @Override
    public Mechanic findById(Long id) {
        return mechanicRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't find mechanic by id: " + id));
    }

    @Override
    public BigDecimal getSalary(Long id) {
        Mechanic mechanic = findById(id);
        List<Work> workList = workService.findAllByMechanicId(id).stream()
                .filter(work -> work.getPaidStatus().equals(Work.PaymentStatus.NOT_PAID))
                .toList();
        BigDecimal salary = workList.stream()
                .map(Work::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        mechanic.setCompletedWorks(workList.stream()
                .peek(w -> w.setPaidStatus(Work.PaymentStatus.PAID_OUT))
                .collect(Collectors.toList()));
        save(mechanic);
        return salary.multiply(PERCENTAGE_OF_MECHANIC_SALARY);
    }
}
