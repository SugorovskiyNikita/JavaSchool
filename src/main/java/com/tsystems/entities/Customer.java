package com.tsystems.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by nikita on 04.09.20.
 */
@Entity
@Table(name = "customers", schema = "tmobile")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "passport_number")
    private String passportNumber;

    @Column(name = "passport_date")
    private String passportDate;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "is_blocked")
    private Integer isBlocked;

    public Customer() {
    }

    public Customer(String name, String lastname, Date dateOfBirth, String passportNumber, String passportDate, String address, String email, String password, Integer isBlocked) {
        this.name = name;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.passportNumber = passportNumber;
        this.passportDate = passportDate;
        this.address = address;
        this.email = email;
        this.password = password;
        this.isBlocked = isBlocked;

    }
}
