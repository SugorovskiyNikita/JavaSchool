package com.tsystems.entities;



import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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

    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;

    //@ManyToMany
    //@JoinTable(name = "users_roles", joinColumns = @JoinColumn(
            //name = "user_id", referencedColumnName = "id"),
            //inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    //private Set<Role> roles;

    public Customer() {
    }

    public Customer(Integer id, String name, String surname, String email, Integer isBlocked) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.isBlocked = isBlocked;
    }

    //public Set<SimpleGrantedAuthority> getAuthorities(){
        //return getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
    //}
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;

        Customer customer = (Customer) o;

        if (!Objects.equals(isBlocked, customer.isBlocked))
            return false;
        if (dateOfBirth != null ? !dateOfBirth.equals(customer.dateOfBirth) : customer.dateOfBirth != null)
            return false;
        if (passportNumber != null ? !passportNumber.equals(customer.passportNumber) : customer.passportNumber != null)
            return false;
        if (passportData != null ? !passportData.equals(customer.passportData) : customer.passportData != null)
            return false;
        if (address != null ? !address.equals(customer.address) : customer.address != null)
            return false;
        return contracts != null ? contracts.equals(customer.contracts) : customer.contracts == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + (passportNumber != null ? passportNumber.hashCode() : 0);
        result = 31 * result + (passportData != null ? passportData.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + isBlocked;
        result = 31 * result + (contracts != null ? contracts.hashCode() : 0);
        return result;
    }
}

