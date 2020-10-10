package com.tsystems.db.entities;

import lombok.Data;
import org.apache.commons.lang3.builder.HashCodeExclude;

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

    @HashCodeExclude
    @JoinTable(name = "Required_option_relationships", joinColumns = {
            @JoinColumn(name = "id_first", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "id_second", referencedColumnName = "id")
    })
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Option> required = new HashSet<>();


    @JoinTable(name = "Forbidden_option_relationships", joinColumns = {
            @JoinColumn(name = "id_first", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "id_second", referencedColumnName = "id", nullable = false)
    })
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Option> forbidden = new HashSet<>();

    @HashCodeExclude
    @JoinTable(name = "Possible_options_of_tariffs", joinColumns = {
            @JoinColumn(name = "option_id", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "tariff_id", referencedColumnName = "id", nullable = false)
    })
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Tariff> possibleTariffsOfOption = new HashSet<>();

    @HashCodeExclude
    @JoinTable(name = "Used_options_of_tariff", joinColumns = {
            @JoinColumn(name = "option_id", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "contract_id", referencedColumnName = "id", nullable = false)
    })
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Contract> contractsThoseUseOption = new HashSet<>();

    public Option() {
        required = new HashSet<>();
        forbidden = new HashSet<>();
        possibleTariffsOfOption = new HashSet<>();
        contractsThoseUseOption = new HashSet<>();
    }

    public Option(Integer id, String name, BigDecimal cost, BigDecimal connectCost, String description) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.connectCost = connectCost;
        this.description = description;

    }


    public Set<Option> getRequired() {
        return required;
    }

    public void setRequired(Set<Option> required) {
        this.required = required;
    }

    public Set<Option> getForbidden() {
        return forbidden;
    }

    public void setForbidden(Set<Option> forbidden) {
        this.forbidden = forbidden;
    }


    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        result = 31 * result + (connectCost != null ? connectCost.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
