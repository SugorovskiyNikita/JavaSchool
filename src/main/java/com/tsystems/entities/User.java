package com.tsystems.entities;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by nikita on 24.09.2020.
 */
@Entity
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    private String name;

    @Basic
    private String surname;

    @Basic
    private String password;

    @Basic
    private String role;

    @Basic
    private String email;
}
