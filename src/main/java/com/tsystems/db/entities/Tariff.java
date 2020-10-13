package com.tsystems.db.entities;

import lombok.*;
import org.apache.commons.lang3.builder.HashCodeExclude;
import org.apache.commons.lang3.builder.ToStringExclude;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by nikita on 13.09.2020.
 */
@Getter
@Setter
@Entity
@Table(name = "Tariffs", schema = "tmobile")
public class Tariff {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "cost")
    private BigDecimal cost;

    @Basic
    @Column(name = "description")
    private String description;

    @ToStringExclude
    @HashCodeExclude
    @JoinTable(name = "Possible_options_of_tariffs", joinColumns = {
            @JoinColumn(name = "tariff_id", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "option_id", referencedColumnName = "id", nullable = false)
    })
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Set<Option> possibleOptions;

    public Tariff() {
    }

    public Tariff(Integer id, String name, BigDecimal cost, String description) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.description = description;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tariff tariff = (Tariff) o;

        if (id != null ? !id.equals(tariff.id) : tariff.id != null) return false;
        if (name != null ? !name.equals(tariff.name) : tariff.name != null) return false;
        if (cost != null ? !cost.equals(tariff.cost) : tariff.cost != null) return false;
        return description != null ? description.equals(tariff.description) : tariff.description == null;

    }
}
