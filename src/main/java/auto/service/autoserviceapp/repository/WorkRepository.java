package auto.service.autoserviceapp.repository;

import auto.service.autoserviceapp.model.Work;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long> {
    List<Work> findAllByMechanicId(Long id);
}
