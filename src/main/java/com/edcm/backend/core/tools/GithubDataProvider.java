package com.edcm.backend.core.tools;

import com.edcm.backend.core.dto.CommoditySharedData;

import java.util.List;


public interface GithubDataProvider {
    List<CommoditySharedData> getCommodities();
}
