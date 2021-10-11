package com.edcm.backend.infrastructure.domain.database.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "station")
@Getter
@Setter
@RequiredArgsConstructor
public class StationEntity {

    @Id
    @Column(name = "station_name")
    private String stationName;

    @OneToMany(mappedBy = "station")
    private List<StationEconomyEntity> economies;

//    @OneToMany(mappedBy = "station")
//    private List<StationCommodityEntity> commoditiesAtStation;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationEntity that = (StationEntity) o;
        return stationName.equals(that.stationName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stationName);
    }
}
