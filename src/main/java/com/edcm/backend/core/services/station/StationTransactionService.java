package com.edcm.backend.core.services.station;

import com.edcm.backend.infrastructure.domain.database.entities.Station;

public interface StationTransactionService {
    Station createOrFindStation(String name, String systemName);

    Station createOfFindCarrier(String code);

    Station saveStation(Station station);
}
