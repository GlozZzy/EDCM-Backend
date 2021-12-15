package com.edcm.backend.core.shared.data;

import com.edcm.backend.core.shared.data.CommoditySharedData;
import lombok.Data;

import java.io.Serializable;

@Data
public class StationCommoditySharedData implements Serializable {
    private final Long id;
    private final CommoditySharedData commodity;
    private final Long stock;
    private final Long demand;
    private final Long buyPrice;
    private final Long sellPrice;
}
