package com.edcm.backend.infrastructure.domain.database.entities.keys;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class StationEconomyKey implements Serializable {
    @Column(name = "station_name")
    private String stationName;

    @Column(name = "economy_name")
    private String economyName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StationEconomyKey that = (StationEconomyKey) o;

        if (!Objects.equals(stationName, that.stationName)) return false;
        return Objects.equals(economyName, that.economyName);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(stationName);
        result = 31 * result + (Objects.hashCode(economyName));
        return result;
    }
}