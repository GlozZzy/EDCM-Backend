package com.edcm.backend.core.services.implementations;

import com.edcm.backend.core.services.SystemTransactionHandler;
import com.edcm.backend.infrastructure.domain.database.entities.SystemEntity;
import com.edcm.backend.infrastructure.domain.database.repositories.SystemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class SystemTransactionHandlerImpl implements SystemTransactionHandler {
    private final SystemRepository repository;

    @Override
    public SystemEntity createOrFindSystem(String name) {
        return repository.findByName(name).orElseGet(() -> {
            var system = new SystemEntity();
            system.setName(name);
            return repository.save(system);
        });
    }
}
