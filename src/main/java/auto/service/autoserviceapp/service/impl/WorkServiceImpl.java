package auto.service.autoserviceapp.service.impl;

import auto.service.autoserviceapp.model.Work;
import auto.service.autoserviceapp.repository.WorkRepository;
import auto.service.autoserviceapp.service.WorkService;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WorkServiceImpl implements WorkService {
    private final WorkRepository workRepository;

    @Override
    public Work save(Work work) {
        return workRepository.save(work);
    }

    @Override
    public List<Work> findAllById(List<Long> workIds) {
        return workRepository.findAllById(workIds);
    }

    @Override
    public Work findById(Long id) {
        return workRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't find work by id: " + id));
    }

    @Override
    public List<Work> findAllByMechanicId(Long id) {
        return workRepository.findAllByMechanicId(id);
    }

    @Override
    public Work updatePaidStatus(
            Long id,
            Work.PaidStatus paidStatus
    ) {
        Work work = findById(id);
        work.setPaidStatus(paidStatus);
        return work;
    }
}
