package com.edcm.backend.extensions.mappers;

import com.edcm.backend.core.dto.CommoditySharedData;
import com.edcm.backend.infrastructure.domain.database.entities.CommodityEntity;
import com.edcm.backend.infrastructure.domain.github.GithubCommodityItem;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommoditiesDataMapper {
    CommoditySharedData toCommoditySharedData(GithubCommodityItem githubCommodityItem);

    CommoditySharedData toCommoditySharedData(CommodityEntity githubCommodityItem);

    CommodityEntity toCommodityEntity(CommoditySharedData commoditySharedData);

}
