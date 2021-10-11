package com.edcm.backend.infrastructure.domain.database.entities;

import com.edcm.backend.infrastructure.domain.database.entities.keys.StationEconomyKey;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "station_economy")
@Getter
@Setter
@RequiredArgsConstructor
public class StationEconomyEntity {

    @EmbeddedId
    private StationEconomyKey id;

    @ManyToOne
    @MapsId("stationName")
    @JoinColumn(name = "station_name")
    private StationEntity station;

    @ManyToOne
    @MapsId("economyName")
    @JoinColumn(name = "economy_name")
    private EconomyEntity economy;

    @NotNull
    @Column(name="proportion")
    private double proportion;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationEconomyEntity that = (StationEconomyEntity) o;
        return Double.compare(that.proportion, proportion) == 0 && station.equals(that.station) && economy.equals(that.economy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(station, economy, proportion);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
            "id = " + id + ", " +
            "economy = " + economy + ", " +
            "proportion = " + proportion + ")";
    }
}
