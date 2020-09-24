package com.tsystems.dto;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by nikita on 24.09.2020.
 */
public class UserDto {

    private Integer id;

    private String name;

    private String surname;

    private String email;

    private List<String> roles = new ArrayList<>();
}
