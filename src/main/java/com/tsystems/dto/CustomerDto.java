package com.tsystems.dto;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by nikita on 12.09.2020.
 */
@Data
public class CustomerDto implements Serializable {

    private Integer id;

    private String name;

    private String lastname;

    private Date dateOfBirth;

    private String passportNumber;

    private String passportData;

    private String address;

    private String email;

    private int isBlocked;

}
