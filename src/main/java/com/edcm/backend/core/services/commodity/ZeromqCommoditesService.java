package com.edcm.backend.core.services.commodity;

import com.edcm.backend.core.zeromq.schemas.ZeromqCommodityPayload;

public interface ZeromqCommoditesService {
    void saveData(ZeromqCommodityPayload payload);
}
