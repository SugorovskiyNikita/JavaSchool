package com.tsystems.dto;


import lombok.Data;


import java.util.Date;
import java.util.TreeSet;
/**
 * Created by nikita on 24.09.2020.
 */
@Data
public class CustomerDto {

    private Integer id;

    private String name;

    private String surname;

    private Date dayOfBirth;

    private String passportNumber;

    private String passportDate;

    private String address;

    private String email;

    private Integer isBlocked;

    private TreeSet<ContractDto> contracts = new TreeSet<>();
}
