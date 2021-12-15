package com.edcm.backend.infrastructure.domain.database.entities;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "stations_economies")
@Data
public class StationEconomyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "station_id", nullable = false)
    private StationEntity station;


    @ManyToOne(optional = false)
    @JoinColumn(name = "economy_id", nullable = false)
    private EconomyEntity economyName;

    @Column(name = "proportion")
    private Double proportion;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StationEconomyEntity)) return false;
        StationEconomyEntity that = (StationEconomyEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
