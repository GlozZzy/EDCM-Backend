package com.edcm.backend.infrastructure.domain.database.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "economy")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EconomyEntity {

    @Id
    @Column(name = "economy_name")
    private String economyName;

    @OneToMany(mappedBy = "economy")
    private List<StationEconomyEntity> economiesOfStation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EconomyEntity that = (EconomyEntity) o;
        return economyName.equals(that.economyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(economyName);
    }
}
