package com.edcm.backend.core.services.system;

import com.edcm.backend.infrastructure.domain.database.entities.System;

public interface SystemTransactionService {
    System createOrFindSystem(String name);
}
