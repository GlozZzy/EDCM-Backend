package com.edcm.backend.core.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.WARN,
    uses = CommoditiesDataMapper.class)
public interface StationDataMapper {
}
