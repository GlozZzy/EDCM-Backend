package com.edcm.backend.core.shared.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
public class CommoditySharedData implements Serializable {
    private final String name;
    private final String eddnName;
    private final CommodityCategorySharedData category;
}
