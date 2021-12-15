package com.edcm.backend.core.tools;

import com.edcm.backend.core.shared.data.CommoditySharedData;

import java.util.List;


public interface GithubDataProvider {
    List<CommoditySharedData> getCommodities();
}
