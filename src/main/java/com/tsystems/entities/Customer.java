package com.tsystems.entities;



import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDate;
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
    private Integer isBlocked = 0;

    public Customer() {
    }

    public Customer(String name, String lastname, String email, Integer isBlocked) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.isBlocked = isBlocked;
    }
}

