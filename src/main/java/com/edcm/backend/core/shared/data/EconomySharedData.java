package com.edcm.backend.core.shared.data;

import lombok.Data;

import java.io.Serializable;

@Data
public class EconomySharedData implements Serializable {
    private final Long id;
    private final String name;
}
