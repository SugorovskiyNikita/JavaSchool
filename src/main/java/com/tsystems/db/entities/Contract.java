package com.tsystems.db.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

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

    public Contract() {
        //Basic constructor
    }

    public Contract(String number, Integer isBlocked, BigDecimal balance, Customer customer) {
        this.number = number;
        this.isBlocked = isBlocked;
        this.balance = balance;
        this.customer = customer;
    }




}
