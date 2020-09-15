package com.tsystems.entities;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;

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

    @Column(name = "name")
    private String name;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "description")
    private String description;

    public Tariff() {
    }

    public Tariff(Integer id, String name, BigDecimal cost, String description) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.description = description;
    }
}
