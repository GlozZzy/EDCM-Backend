package com.edcm.backend.core.mappers;

import com.edcm.backend.core.shared.data.CommoditySharedData;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommoditiesDataMapper {
    CommoditySharedData toSharedData(String stringCommodity);

}
