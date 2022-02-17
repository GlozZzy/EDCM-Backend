package com.edcm.backend.core.services.economy;

import com.edcm.backend.infrastructure.domain.database.entities.Economy;
import com.edcm.backend.infrastructure.domain.database.entities.StationEconomy;
import com.edcm.backend.infrastructure.domain.database.repositories.EconomyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EconomyTransactionServiceImpl implements EconomyTransactionService {
    private final EconomyRepository repository;

    @Override
    public StationEconomy createOrFindEconomy(String economy) {
        if (economy != null) {
            Economy economyEntity = repository.findByName(economy)
                .orElseGet(() -> {
                    var newEconomy = new Economy();
                    newEconomy.setName(economy);
                    return repository.save(newEconomy);
                });

            StationEconomy stationEconomyEntity = new StationEconomy();
            stationEconomyEntity.setEconomyName(economyEntity);
            return stationEconomyEntity;
        }
        return null;
    }
}
