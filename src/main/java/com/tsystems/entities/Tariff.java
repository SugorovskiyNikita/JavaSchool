package com.tsystems.entities;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by nikita on 13.09.2020.
 */
@Data
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
}
