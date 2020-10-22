package com.tsystems.db.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.builder.HashCodeExclude;
import org.apache.commons.lang3.builder.ToStringExclude;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by nikita on 04.09.20.
 */
@RequiredArgsConstructor
@Entity
@Table(name = "contracts", schema = "tmobile")
@Data

public class Contract {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "number")
    private String number;

    @Column(name = "is_Blocked")
    private Integer isBlocked;

    @Column(name = "balance")
    private BigDecimal balance;

    @ToStringExclude
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer")
    private Customer customer;


    @ManyToOne
    @JoinColumn(name = "tariff")
    private Tariff tariff;

    @HashCodeExclude
    @JoinTable(name = "Used_options_of_tariff", joinColumns = {
            @JoinColumn(name = "contract_id", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "option_id", referencedColumnName = "id", nullable = false)
    })
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Option> usedOptions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contract contract = (Contract) o;

        if (id != null ? !id.equals(contract.id) : contract.id != null) return false;
        if (number != null ? !number.equals(contract.number) : contract.number != null) return false;
        if (isBlocked != null ? !isBlocked.equals(contract.isBlocked) : contract.isBlocked != null) return false;
        if (balance != null ? !balance.equals(contract.balance) : contract.balance != null) return false;
        return true;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (isBlocked != null ? isBlocked.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        return result;
    }
}
