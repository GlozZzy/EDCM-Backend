package com.edcm.backend.infrastructure.domain.database.entities;

import com.edcm.backend.infrastructure.domain.database.entities.keys.StationCommodityKey;
import lombok.*;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Objects;

//@Entity
@Table(name = "station_commodity")
@Getter
@Setter
@RequiredArgsConstructor
public class StationCommodityEntity {

    @EmbeddedId
    private StationCommodityKey key;

    @ManyToOne
    @MapsId("stationName")
    @JoinColumn(name = "station_name")
    private StationEntity station;

    @ManyToOne
    @MapsId("commodityName")
    @JoinColumn(name = "commodity_name")
    private CommodityEntity commodity;

    @Column(name = "buy_price")
    private Integer buyPrice;

    @Column(name = "sell_price")
    private Integer sellPrice;

    @Formula("sell_price - buy_price")
    private Integer profit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationCommodityEntity that = (StationCommodityEntity) o;
        return station.equals(that.station)
            && commodity.equals(that.commodity)
            && Objects.equals(buyPrice, that.buyPrice)
            && Objects.equals(sellPrice, that.sellPrice)
            && Objects.equals(profit, that.profit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(station, commodity, buyPrice, sellPrice, profit);
    }
}
