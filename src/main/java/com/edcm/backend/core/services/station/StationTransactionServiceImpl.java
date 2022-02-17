package com.edcm.backend.core.services.station;

import com.edcm.backend.infrastructure.domain.database.entities.Station;
import com.edcm.backend.infrastructure.domain.database.repositories.StationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class StationTransactionServiceImpl implements StationTransactionService {
    private final StationRepository stationRepository;

    @Override
    public Station createOrFindStation(String stationName, String systemName) {
        return stationRepository.findByNameAndSystem_Name(stationName, systemName)
            .orElseGet(() -> Station.builder()
                .name(stationName)
                .build());
    }

    @Override
    public Station createOfFindCarrier(String code) {
        return stationRepository.findByName(code)
            .orElseGet(() -> Station.builder()
                .name(code)
                .build());
    }

    @Override
    @Modifying
    public Station saveStation(Station station) {
        return stationRepository.saveAndFlush(station);
    }
}