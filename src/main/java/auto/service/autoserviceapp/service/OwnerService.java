package auto.service.autoserviceapp.service;

import auto.service.autoserviceapp.model.Owner;

public interface OwnerService {
    Owner save(Owner owner);

    Owner findById(Long id);
}
