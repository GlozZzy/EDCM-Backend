package com.edcm.backend.core.services;

import com.edcm.backend.core.zeromq.schemas.ZeromqCommodityPayload;

public interface ZeromqCommoditesService {
    void saveData(ZeromqCommodityPayload payload);
}
