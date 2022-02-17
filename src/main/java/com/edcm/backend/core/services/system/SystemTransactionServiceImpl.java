package com.edcm.backend.core.services.system;

import com.edcm.backend.infrastructure.domain.database.entities.System;
import com.edcm.backend.infrastructure.domain.database.repositories.SystemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SystemTransactionServiceImpl implements SystemTransactionService {
    private final SystemRepository repository;

    @Override
    public System createOrFindSystem(String name) {
        return repository.findByName(name).orElseGet(() -> {
            var system = new System();
            system.setName(name);
            return repository.save(system);
        });
    }
}
