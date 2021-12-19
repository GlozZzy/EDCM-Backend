package com.edcm.backend.infrastructure.domain.database.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "commodity", indexes = {
    @Index(name = "idx_commodity_name_unq", columnList = "name", unique = true),
    @Index(name = "idx_commodity_eddn_name_unq", columnList = "eddn_name", unique = true)
})
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Immutable
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
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CommodityEntity commodity = (CommodityEntity) o;
        return id != null && Objects.equals(id, commodity.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
