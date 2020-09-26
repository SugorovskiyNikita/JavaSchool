package com.tsystems.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


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

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dateOfBirth;

    private String passportNumber;

    private String passportData;

    private String address;

    private String password;

    private String email;

    private Integer isBlocked;

    private TreeSet<ContractDto> contracts = new TreeSet<>();

    public CustomerDto() { getId();
    getEmail();
    getName();
    getSurname();
    getIsBlocked();

    }

    public CustomerDto(Integer id, String name, String surname, Date dateOfBirth, String passportNumber, String passportData, String address, String password, String email, Integer isBlocked) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.passportNumber = passportNumber;
        this.passportData = passportData;
        this.address = address;
        this.password = password;
        this.email = email;
        this.isBlocked = isBlocked;
    }
    public CustomerDto(Integer id, String name, String surname, String email, Integer isBlocked) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.isBlocked = isBlocked;
    }
}
