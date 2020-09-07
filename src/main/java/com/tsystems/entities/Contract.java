package com.tsystems.entities;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by nikita on 04.09.20.
 */
@Entity
@Table(name = "contracts",schema = "tmobile")
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(Integer isBlocked) {
        this.isBlocked = isBlocked;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", isBlocked=" + isBlocked +
                ", balance=" + balance +
                ", customer=" + customer +
                '}';
    }

}
