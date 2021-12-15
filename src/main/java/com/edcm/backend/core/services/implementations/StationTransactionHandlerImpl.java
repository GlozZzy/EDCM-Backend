package com.edcm.backend.core.services.implementations;

import com.edcm.backend.core.services.StationTransactionHandler;
import com.edcm.backend.infrastructure.domain.database.entities.StationEntity;
import com.edcm.backend.infrastructure.domain.database.repositories.ProhibitedCommodityRepository;
import com.edcm.backend.infrastructure.domain.database.repositories.StationCommodityRepository;
import com.edcm.backend.infrastructure.domain.database.repositories.StationEconomyRepository;
import com.edcm.backend.infrastructure.domain.database.repositories.StationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class StationTransactionHandlerImpl implements StationTransactionHandler {
    private final StationRepository stationRepository;
    private final ProhibitedCommodityRepository prohibitedCommodityRepository;
    private final StationCommodityRepository stationCommodityRepository;
    private final StationEconomyRepository stationEconomyRepository;

    @Override
    public StationEntity createOrFindStation(String name) {
        return stationRepository.findByName(name)
            .orElseGet(() -> StationEntity.builder()
                .name(name)
                .build());
    }

    @Override
    public StationEntity saveStation(StationEntity station) {
        return stationRepository.save(station);
    }

}
