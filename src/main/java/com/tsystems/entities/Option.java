package com.tsystems.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by nikita on 16.09.2020.
 */
@Data
@Entity
@Table(name = "Options", schema = "tmobile")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "cost")
    private BigDecimal cost;

    @Basic
    @Column(name = "connect_cost")
    private BigDecimal connectCost;

    @Basic
    @Column(name = "description")
    private String description;

    @JoinTable(name = "Required_option_relationships", joinColumns = {
            @JoinColumn(name = "id_first", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "id_second", referencedColumnName = "id")})
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Option> required;

    @JoinTable(name = "Forbidden_option_relationships", joinColumns = {
            @JoinColumn(name = "id_first", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "id_second", referencedColumnName = "id", nullable = false)
    })
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Option> forbidden;

    @JoinTable(name = "Possible_options_of_tariffs", joinColumns = {
            @JoinColumn(name = "option_id", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "tariff_id", referencedColumnName = "id", nullable = false)
    })
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Tariff> possibleTariffsOption;

    @JoinTable(name = "Used_options_of_tariff", joinColumns = {
            @JoinColumn(name = "option_id", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "contract_id", referencedColumnName = "id", nullable = false)
    })
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Contract> contractsThoseUseOption;

    public Option() {
        required = new HashSet<>();
        forbidden = new HashSet<>();
        possibleTariffsOption = new HashSet<>();
        contractsThoseUseOption = new HashSet<>();
    }

    public void addToRequiredOption(Option option) {
        this.getRequired().add(option);
        option.getRequired().add(this);
    }
}
