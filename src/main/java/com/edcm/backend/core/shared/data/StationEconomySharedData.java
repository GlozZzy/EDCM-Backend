package com.edcm.backend.core.shared.data;

import lombok.Data;

import java.io.Serializable;

@Data
public class StationEconomySharedData implements Serializable {
    private final Long id;
    private final EconomySharedData economyName;
    private final Double proportion;
}
