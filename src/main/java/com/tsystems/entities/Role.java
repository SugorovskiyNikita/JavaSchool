package com.tsystems.entities;

import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

/**
 * Created by nikita on 24.09.2020.
 */
@Entity
@Table(name = "roles")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_role")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<Customer> customers;

}
