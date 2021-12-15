package com.edcm.backend.infrastructure.domain.database.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "prohibited_at_station")
@ToString
public class ProhibitedCommodityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JoinColumn(name = "station_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private StationEntity station;

    @JoinColumn(name = "commodity_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CommodityEntity commodity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProhibitedCommodityEntity)) return false;
        ProhibitedCommodityEntity that = (ProhibitedCommodityEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
