package com.tsystems.entities;



import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

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

    @Column(name = "surname")
    private String surname;

    @Column(name = "date_of_birth")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dateOfBirth;

    @Column(name = "passport_number")
    private String passportNumber;

    @Column(name = "passport_data")
    private String passportData;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "is_blocked")
    private Integer isBlocked;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private Set<Contract> contracts;

    public Customer() {
    }

    public Customer(Integer id, String name, String surname, String email, Integer isBlocked) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.isBlocked = isBlocked;
    }
}

