package com.edcm.backend.core.mappers;

import com.edcm.backend.core.shared.data.CommoditySharedData;
import com.edcm.backend.infrastructure.domain.github.GithubCommodityItem;
import com.edcm.backend.infrastructure.domain.github.GithubCommodityItemWithEddn;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GithubCommodityMapper {
    @Mappings({
        @Mapping(target = "category.name", source = "category")
    })
    CommoditySharedData toSharedData(GithubCommodityItemWithEddn responseItem);
}
