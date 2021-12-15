package com.edcm.backend.infrastructure.domain.database.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "commodities_at_station")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StationCommodityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JoinColumn(name = "station_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @ToString.Exclude
    private StationEntity station;

    @JoinColumn(name = "commodity_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @ToString.Exclude
    private CommodityEntity commodity;

    @Column(name = "stock")
    private Long stock;

    @Column(name = "demand")
    private Long demand;

    @Column(name = "buy_price")
    private Long buyPrice;

    @Column(name = "sell_price")
    private Long sellPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StationCommodityEntity)) return false;
        StationCommodityEntity that = (StationCommodityEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
