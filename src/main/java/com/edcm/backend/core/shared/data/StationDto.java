package com.edcm.backend.core.shared.data;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class StationDto implements Serializable {
    private final String name;
    private final String systemName;
    private final List<CommoditySharedData> prohibited;
    private final List<StationCommoditySharedData> commodities;
    private final List<StationEconomySharedData> economies;
}
