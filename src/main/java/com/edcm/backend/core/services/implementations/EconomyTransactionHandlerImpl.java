package com.edcm.backend.core.services.implementations;

import com.edcm.backend.core.services.EconomyTransactionHandler;
import com.edcm.backend.infrastructure.domain.database.entities.EconomyEntity;
import com.edcm.backend.infrastructure.domain.database.entities.StationEconomyEntity;
import com.edcm.backend.infrastructure.domain.database.repositories.EconomyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class EconomyTransactionHandlerImpl implements EconomyTransactionHandler {
    private final EconomyRepository repository;

    @Override
    public StationEconomyEntity createOrFindEconomy(String economy) {
        if (economy != null) {
            EconomyEntity economyEntity = repository.findByName(economy)
                .orElseGet(() -> {
                    var newEconomy = new EconomyEntity();
                    newEconomy.setName(economy);
                    return repository.save(newEconomy);
                });

            StationEconomyEntity stationEconomyEntity = new StationEconomyEntity();
            stationEconomyEntity.setEconomyName(economyEntity);
            return stationEconomyEntity;
        }
        return null;
    }
}
