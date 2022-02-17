package com.edcm.backend.core.services.economy;

import com.edcm.backend.infrastructure.domain.database.entities.StationEconomy;

public interface EconomyTransactionService {
    StationEconomy createOrFindEconomy(String economy);
}
