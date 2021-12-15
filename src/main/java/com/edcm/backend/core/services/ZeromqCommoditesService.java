package com.edcm.backend.core.services;

import com.edcm.backend.core.zeromq.schemas.Commodity;
import com.edcm.backend.core.zeromq.schemas.CommodityContent;
import com.edcm.backend.core.zeromq.schemas.ZeromqCommodityPayload;
import com.edcm.backend.infrastructure.domain.database.entities.CommodityEntity;
import com.edcm.backend.infrastructure.domain.database.entities.ProhibitedCommodityEntity;
import com.edcm.backend.infrastructure.domain.database.entities.StationCommodityEntity;
import com.edcm.backend.infrastructure.domain.database.entities.StationEntity;
import org.springframework.transaction.annotation.Transactional;

public interface ZeromqCommoditesService {
    void saveData(ZeromqCommodityPayload payload);

    void saveStation(StationEntity station);

    void updateEconomies(StationEntity station, CommodityContent content);

    void updateProhibited(StationEntity station, CommodityContent content);

    void updateStationCommodities(StationEntity station, CommodityContent content);

    StationCommodityEntity initNewStationCommodity(Commodity commodity);

    ProhibitedCommodityEntity initProhibitedCommodity(String prohibitedCommodity);
}
