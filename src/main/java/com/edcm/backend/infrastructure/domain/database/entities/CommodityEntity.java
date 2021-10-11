package com.edcm.backend.infrastructure.domain.database.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

//@Entity
@Table(name = "commodity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommodityEntity {

    @Id
    @Column(name = "commodity_name")
    private String commodityName;

    @Column(name = "category")
    @NotNull
    private String category;

    @OneToMany(mappedBy = "commodity")
    private List<StationCommodityEntity> commoditiesAtStation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommodityEntity that = (CommodityEntity) o;
        return commodityName.equals(that.commodityName) && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commodityName, category);
    }
}
