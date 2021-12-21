package com.edcm.backend.core.services;

import com.edcm.backend.infrastructure.domain.database.entities.StationEntity;

public interface StationTransactionHandler {
    StationEntity createOrFindStation(String name, String systemName);

    StationEntity createOfFindCarrier(String code);

    StationEntity saveStation(StationEntity station);
}
