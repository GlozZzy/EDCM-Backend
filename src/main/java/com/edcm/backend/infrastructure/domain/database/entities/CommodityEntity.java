package com.edcm.backend.infrastructure.domain.database.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "commodity", indexes = {
    @Index(name = "idx_commodity_name_unq", columnList = "name", unique = true),
    @Index(name = "idx_commodity_eddn_name_unq", columnList = "eddn_name", unique = true)
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommodityEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "eddn_name", nullable = false)
    private String eddnName;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private CommodityCategoryEntity category;

    public CommodityEntity(String name, String eddnName, CommodityCategoryEntity category) {
        this.name = name;
        this.eddnName = eddnName;
        this.category = category;
    }

    public String getEddnName() {
        return eddnName;
    }

    public void setEddnName(String eddnName) {
        this.eddnName = eddnName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommodityEntity)) return false;
        CommodityEntity entity = (CommodityEntity) o;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
