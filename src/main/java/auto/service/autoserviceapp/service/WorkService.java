package auto.service.autoserviceapp.service;

import auto.service.autoserviceapp.model.Work;
import java.util.List;

public interface WorkService {
    Work save(Work work);

    List<Work> findAllById(List<Long> workIds);

    Work findById(Long id);

    List<Work> findAllByMechanicId(Long id);

    Work updatePaidStatus(Long id, Work.PaidStatus paidStatus);
}
