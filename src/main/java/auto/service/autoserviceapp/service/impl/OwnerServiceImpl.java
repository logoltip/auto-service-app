package auto.service.autoserviceapp.service.impl;

import auto.service.autoserviceapp.model.Owner;
import auto.service.autoserviceapp.repository.OwnerRepository;
import auto.service.autoserviceapp.service.OwnerService;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;

    @Override
    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public Owner findById(Long id) {
        return ownerRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't find owner by id: " + id));
    }
}
