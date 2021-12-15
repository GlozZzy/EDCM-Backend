package com.edcm.backend.core.mappers;

import com.edcm.backend.core.shared.data.CommoditySharedData;
import com.edcm.backend.infrastructure.domain.database.entities.CommodityEntity;
import com.edcm.backend.infrastructure.domain.database.entities.ProhibitedCommodityEntity;
import com.edcm.backend.infrastructure.domain.github.GithubCommodityItem;
import com.edcm.backend.infrastructure.domain.github.GithubCommodityItemWithEddn;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommoditiesDataMapper {
    CommoditySharedData toSharedData(String stringCommodity);

}
