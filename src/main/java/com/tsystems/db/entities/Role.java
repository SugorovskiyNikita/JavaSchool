package com.tsystems.db.entities;

import lombok.Data;

import javax.persistence.*;

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

    @Column(name = "role_name")
    private String roleName;

}
