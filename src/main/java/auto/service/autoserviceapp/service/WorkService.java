package auto.service.autoserviceapp.service;

import auto.service.autoserviceapp.model.Work;
import java.util.List;

public interface WorkService {
    Work save(Work work);

    List<Work> findAllByIds(List<Long> workIds);

    Work findById(Long id);

    List<Work> findAllByMechanicId(Long mechanicId);

    Work updatePaidStatus(Long id, Work.PaymentStatus paidStatus);
}
