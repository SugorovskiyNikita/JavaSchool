package com.tsystems.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.TreeSet;
/**
 * Created by nikita on 24.09.2020.
 */
@Data
public class TariffDto {
    private Integer id;

    private String name;

    private BigDecimal cost;

    private String description;

    private TreeSet<OptionDto> possibleOptions = new TreeSet<>();

}
