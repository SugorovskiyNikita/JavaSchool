package com.tsystems.entities;



import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by nikita on 04.09.20.
 */
@Entity
@Table(name = "customers")
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

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

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


    //@ManyToMany
    //@JoinTable(name = "users_roles", joinColumns = @JoinColumn(
            //name = "user_id", referencedColumnName = "id"),
            //inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    //private Set<Role> roles;

    public Customer() {
    }

    public Customer(Integer id, String name, String surname, String email, Integer isBlocked) {
        this.setId(id);
        this.setName(name);
        this.setSurname(surname);
        this.setEmail(email);
        this.isBlocked = isBlocked;
    }

    //public Set<SimpleGrantedAuthority> getAuthorities(){
        //return getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
    //}

}

