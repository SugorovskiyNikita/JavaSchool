package com.tsystems.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by nikita on 04.09.20.
 */
@Entity
@Table(name = "contracts",schema = "tmobile")
@Data
public class Contract {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @Column(name = "number")
    private String number;

    @Column(name = "is_Blocked")
    private Integer isBlocked;

    @Column(name = "balance")
    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "tariff")
    private Tariff tariff;

    @ManyToMany()
    private Set<Option> usedOptions;

    public Contract() {
    }

    public Contract(Integer id, String number, Integer isBlocked, BigDecimal balance, Customer customer, Tariff tariff) {
        this.id = id;
        this.number = number;
        this.isBlocked = isBlocked;
        this.balance = balance;
        this.customer = customer;
        this.tariff = tariff;
    }
}
