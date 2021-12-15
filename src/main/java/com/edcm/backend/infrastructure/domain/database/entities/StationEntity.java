package com.edcm.backend.infrastructure.domain.database.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "station", indexes = {
    @Index(name = "idx_stationentity_name_unq", columnList = "name", unique = true)
})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StationEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "system", nullable = false)
    private SystemEntity system;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "station", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<ProhibitedCommodityEntity> prohibited = new ArrayList<>();

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "station", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<StationCommodityEntity> commodities = new ArrayList<>();

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "station", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<StationEconomyEntity> economies = new ArrayList<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StationEntity)) return false;
        StationEntity that = (StationEntity) o;
        return Objects.equals(id, that.id);
    }

    public void addCommodity(StationCommodityEntity commodity) {
        commodity.setStation(this);
        commodities.add(commodity);
    }

    public void addProhibited(ProhibitedCommodityEntity prohibited) {
        prohibited.setStation(this);
        this.prohibited.add(prohibited);
    }

    public void addEconomy(StationEconomyEntity economy){
        economy.setStation(this);
        economies.add(economy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
