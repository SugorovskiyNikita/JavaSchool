package com.tsystems.entities;



import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by nikita on 04.09.20.
 */
@Entity
@Table(name = "customers")
@Data
public class Customer extends User {


    @Column(name = "date_of_birth")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dateOfBirth;

    @Column(name = "passport_number")
    private String passportNumber;

    @Column(name = "passport_data")
    private String passportData;

    @Column(name = "address")
    private String address;

    @Column(name = "is_blocked")
    private Integer isBlocked;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Contract> contracts;

    public Customer() {
    }

    public Customer(Integer id, String name, String surname, String email, Integer isBlocked) {
        this.setId(id);
        this.setName(name);
        this.setSurname(surname);
        this.setEmail(email);
        this.isBlocked = isBlocked;
    }
}

