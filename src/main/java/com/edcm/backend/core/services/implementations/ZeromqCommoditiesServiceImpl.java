package com.edcm.backend.core.services.implementations;

import com.edcm.backend.core.services.CategoryTransactionHandler;
import com.edcm.backend.core.services.CommodityTransactionHandler;
import com.edcm.backend.core.services.EconomyTransactionHandler;
import com.edcm.backend.core.services.StationTransactionHandler;
import com.edcm.backend.core.services.SystemTransactionHandler;
import com.edcm.backend.core.services.ZeromqCommoditesService;
import com.edcm.backend.core.zeromq.schemas.Commodity;
import com.edcm.backend.core.zeromq.schemas.CommodityContent;
import com.edcm.backend.core.zeromq.schemas.ZeromqCommodityPayload;
import com.edcm.backend.infrastructure.domain.database.entities.CommodityEntity;
import com.edcm.backend.infrastructure.domain.database.entities.ProhibitedCommodityEntity;
import com.edcm.backend.infrastructure.domain.database.entities.StationCommodityEntity;
import com.edcm.backend.infrastructure.domain.database.entities.StationEconomyEntity;
import com.edcm.backend.infrastructure.domain.database.entities.StationEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.PersistentObjectException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ZeromqCommoditiesServiceImpl implements ZeromqCommoditesService {
    private final CategoryTransactionHandler categoryHandler;
    private final CommodityTransactionHandler commodityHandler;
    private final EconomyTransactionHandler economyHandler;
    private final StationTransactionHandler stationHandler;
    private final SystemTransactionHandler systemHandler;

    @Override
    @Transactional
    public void saveData(ZeromqCommodityPayload payload) {
        CommodityContent content = payload.getContent();
        var station = stationHandler.createOrFindStation(content.getStationName());
        var system = systemHandler.createOrFindSystem(content.getSystemName());
        station.setSystem(system);
        updateEconomies(station, content);
        updateProhibited(station, content);
        updateStationCommodities(station, content);
        saveStation(station);
    }

    @Override
    public void saveStation(StationEntity station) {
        try {
            stationHandler.saveStation(station);
        } catch (ConstraintViolationException | PersistentObjectException | DataAccessException e) {
            log.error("Error saving commodity info", e);
        }
    }

    @Override
    public void updateEconomies(StationEntity station, CommodityContent content) {
        station.getEconomies().clear();
        if (content.getEconomies() != null) {
            var economies = content.getEconomies()
                .parallelStream()
                .map(economy -> economyHandler.createOrFindEconomy(economy.getName()))
                .peek(economy -> economy.setStation(station))
                .toList();
            station.getEconomies().addAll(economies);
        }
    }

    @Override
    public void updateProhibited(StationEntity station, CommodityContent content) {
        station.getProhibited().clear();
        if (content.getProhibited() != null) {
            var prohibitedCommodityEntities = content.getProhibited()
                .parallelStream()
                .map(this::initProhibitedCommodity)
                .peek(prohibited -> prohibited.setStation(station))
                .toList();
            station.getProhibited().addAll(prohibitedCommodityEntities);
        }
    }

    @Override
    public void updateStationCommodities(StationEntity station, CommodityContent content) {
        station.getCommodities().clear();
        if (content.getCommodities() != null) {
            var commodities = content.getCommodities()
                .stream()
                .map(this::initNewStationCommodity)
                .peek(commodity -> commodity.setStation(station))
                .toList();
            station.getCommodities().addAll(commodities);
        }
    }

    public StationCommodityEntity initNewStationCommodity(Commodity commodity) {
        if (commodity != null) {
            CommodityEntity commodityType = commodityHandler.createOrFindCommodity(commodity.getEddnName());
            return StationCommodityEntity.builder()
                .commodity(commodityType)
                .buyPrice(commodity.getBuyPrice())
                .demand(commodity.getDemand())
                .sellPrice(commodity.getSellPrice())
                .stock(commodity.getStock())
                .build();
        }
        return null;
    }

    public ProhibitedCommodityEntity initProhibitedCommodity(String prohibitedCommodity) {
        if (prohibitedCommodity != null) {
            var commodityEntity = new ProhibitedCommodityEntity();
            commodityEntity
                .setCommodity(commodityHandler.createOrFindCommodity(prohibitedCommodity.toLowerCase()));
            return commodityEntity;
        }
        return null;
    }
}
