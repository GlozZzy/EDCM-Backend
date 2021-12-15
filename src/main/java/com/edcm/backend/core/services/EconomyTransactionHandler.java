package com.edcm.backend.core.services;

import com.edcm.backend.infrastructure.domain.database.entities.StationEconomyEntity;

public interface EconomyTransactionHandler {
    StationEconomyEntity createOrFindEconomy(String economy);
}
