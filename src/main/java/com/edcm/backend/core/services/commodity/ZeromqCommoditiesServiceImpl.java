package com.edcm.backend.core.services.commodity;

import com.edcm.backend.core.services.StationTransactionService;
import com.edcm.backend.core.services.category.CategoryTransactionService;
import com.edcm.backend.core.services.economy.EconomyTransactionService;
import com.edcm.backend.core.services.system.SystemTransactionService;
import com.edcm.backend.core.zeromq.schemas.Commodity;
import com.edcm.backend.core.zeromq.schemas.CommodityContent;
import com.edcm.backend.core.zeromq.schemas.ZeromqCommodityPayload;
import com.edcm.backend.infrastructure.domain.database.entities.CommodityCategory;
import com.edcm.backend.infrastructure.domain.database.entities.CommodityEntity;
import com.edcm.backend.infrastructure.domain.database.entities.ProhibitedCommodity;
import com.edcm.backend.infrastructure.domain.database.entities.Station;
import com.edcm.backend.infrastructure.domain.database.entities.StationCommodity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.PersistentObjectException;
import org.jetbrains.annotations.NotNull;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import javax.validation.ConstraintViolationException;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ZeromqCommoditiesServiceImpl implements ZeromqCommoditesService {
    private final CategoryTransactionService categoryService;
    private final CommodityTransactionService commodityService;
    private final EconomyTransactionService economyService;
    private final StationTransactionService stationService;
    private final SystemTransactionService systemService;

    @Override
    @Transactional(
        isolation = Isolation.READ_COMMITTED,
        propagation = Propagation.REQUIRES_NEW,
        rollbackFor = Throwable.class)
    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    public void saveData(ZeromqCommodityPayload payload) {
        CommodityContent content = payload.getContent();
        Station station = getStation(content);
        var system = systemService.createOrFindSystem(content.getSystemName());
        var commodityReferences = getMapOfCommodities(content);
        station.setSystem(system);
        updateEconomies(station, content);
        updateProhibited(station, content, commodityReferences);
        updateStationCommodities(station, content, commodityReferences);
        try {
            saveStation(station);
        } catch (ConstraintViolationException | PersistentObjectException | DataAccessException e) {
            log.error("Error saving commodity info \n" + content, e);
        }
    }

    private Station getStation(CommodityContent content) {
        if (content.getSystemName().matches("([A-Z0-9]){3}-([A-Z0-9]){3}")) {
            return stationService.createOfFindCarrier(content.getStationName());
        } else {
            return stationService.createOrFindStation(
                content.getStationName(),
                content.getSystemName());
        }
    }

    public void saveStation(Station station) {
        boolean isNewStation = station.getId() == null;
        stationService.saveStation(station);
        if (isNewStation) {
            log.debug(String.format("Saved \"%s\" station info", station.getName()));
        } else {
            log.debug(String.format("Updated \"%s\" station info", station.getName()));
        }
    }

    private void updateEconomies(Station station, CommodityContent content) {
        station.getEconomies().clear();
        if (content.getEconomies() != null) {
            var economies = content.getEconomies()
                .stream()
                .map(economy -> {
                    var stationEconomyEntity = economyService.createOrFindEconomy(economy.getName());
                    Double proportion = economy.getProportion();
                    stationEconomyEntity.setProportion(proportion != null ? proportion : 1.0);
                    return stationEconomyEntity;
                })
                .peek(economy -> economy.setStation(station))
                .toList();
            station.getEconomies().addAll(economies);
        }
    }

    private void updateProhibited(
        Station station,
        CommodityContent content,
        Map<String, CommodityEntity> commodityEntityMap) {
        station.getProhibited().clear();
        if (content.getProhibited() != null) {
            var prohibitedCommodityEntities = content.getProhibited()
                .stream()
                .map(prohibited -> {
                        String eddnName = prohibited.toLowerCase(Locale.ROOT);
                        CommodityEntity commodityReference = getCommodityEntity(commodityEntityMap, eddnName);
                        return new ProhibitedCommodity(station, commodityReference);
                    }
                )
                .toList();
            station.getProhibited().addAll(prohibitedCommodityEntities);
        }
    }

    private void updateStationCommodities(
        Station station,
        CommodityContent content,
        Map<String, CommodityEntity> commodityEntityMap) {
        station.getCommodities().clear();
        if (content.getCommodities() != null) {
            var commodities = content.getCommodities()
                .stream()
                .map(commodity -> {
                    String eddnName = commodity.getEddnName().toLowerCase(Locale.ROOT);
                    CommodityEntity commodityReference = commodityEntityMap.get(eddnName);
                    return StationCommodity.builder()
                        .commodity(commodityReference)
                        .buyPrice(commodity.getBuyPrice())
                        .sellPrice(commodity.getSellPrice())
                        .demand(commodity.getDemand())
                        .stock(commodity.getStock())
                        .station(station)
                        .build();
                })
                .toList();
            station.getCommodities().addAll(commodities);
        }
    }

    private CommodityEntity getCommodityEntity(Map<String, CommodityEntity> commodityEntityMap, String eddnName) {
        return commodityEntityMap.get(eddnName);
    }

    private Map<String, CommodityEntity> getMapOfCommodities(@NotNull CommodityContent content) {
        Set<String> commodities = content.getCommodities()
            .stream()
            .map(Commodity::getEddnName)
            .collect(Collectors.toSet());

        if (content.getProhibited() != null && content.getProhibited().size() > 0) {
            commodities.addAll(content.getProhibited().
                stream()
                .map(item -> item.toLowerCase(Locale.ROOT))
                .collect(Collectors.toSet()));
        }
        var commodityReferencesMap = commodityService.findAllByEddnName(commodities)
            .stream()
            .collect(Collectors.toMap(
                CommodityEntity::getEddnName,
                item -> item
            ));

        commodities.forEach(commodity -> {
            if (commodityReferencesMap.get(commodity.toLowerCase()) == null) {
                CommodityCategory category = categoryService.createOrFindCategory("Unknown");
                CommodityEntity newCommodity = new CommodityEntity(commodity, commodity, category);
                CommodityEntity managedCommodity = commodityService.saveCommodity(newCommodity);

                commodityReferencesMap.put(managedCommodity.getEddnName(), managedCommodity);
            }
        });

        return commodityReferencesMap;
    }
}
