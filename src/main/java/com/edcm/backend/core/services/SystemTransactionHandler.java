package com.edcm.backend.core.services;

import com.edcm.backend.infrastructure.domain.database.entities.SystemEntity;

public interface SystemTransactionHandler {
    SystemEntity createOrFindSystem(String name);
}
