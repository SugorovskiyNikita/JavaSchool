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
            @JoinColumn(name = "id_second", referencedColumnName = "id")
    })
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Option> required = new HashSet<>();

    @JoinTable(name = "Required_option_relationships", joinColumns = { // By using 'mappedby' there dependencies
            @JoinColumn(name = "id_second", referencedColumnName = "id")}, inverseJoinColumns = {  // will not persist
            @JoinColumn(name = "id_first", referencedColumnName = "id")
    })
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Option> requiredMe = new HashSet<>();

    @JoinTable(name = "Forbidden_option_relationships", joinColumns = {
            @JoinColumn(name = "id_first", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "id_second", referencedColumnName = "id", nullable = false)
    })
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Option> forbidden = new HashSet<>();

    @JoinTable(name = "Possible_options_of_tariffs", joinColumns = {
            @JoinColumn(name = "option_id", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "tariff_id", referencedColumnName = "id", nullable = false)
    })
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Set<Tariff> possibleTariffsOfOption = new HashSet<>();

    @JoinTable(name = "Used_options_of_tariff", joinColumns = {
            @JoinColumn(name = "option_id", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "contract_id", referencedColumnName = "id", nullable = false)
    })
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Contract> contractsThoseUseOption = new HashSet<>();

    public Option() {
        required = new HashSet<>();
        forbidden = new HashSet<>();
        possibleTariffsOfOption = new HashSet<>();
        contractsThoseUseOption = new HashSet<>();
    }

    public void addRequiredFromOptions(Option option) {
        this.getRequired().add(option);
    }

    public void addRequiredMeOptions(Option option) {
        this.getRequiredMe().add(option);
    }

    public void addForbiddenWithOptions(Option option) {
        this.getForbidden().add(option);
        option.getForbidden().add(this);
    }

    public void addRequiredFromOptions(Set<Option> options) {
        this.getRequired().addAll(options);
    }

    public void addRequiredMeOptions(Set<Option> options) {
        this.getRequiredMe().addAll(options);
    }

    public void addForbiddenWithOptions(Set<Option> options) {
        this.getForbidden().addAll(options);
        for (Option opt : options)
            opt.getForbidden().add(this);
    }
}
