package com.tsystems.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.TreeSet;
/**
 * Created by nikita on 24.09.2020.
 */
@Data
public class OptionDto {

    private Integer id;

    private String name;

    private BigDecimal cost;

    private BigDecimal connectCost;

    private String description;

    private TreeSet<OptionDto> requiredFrom = new TreeSet<>();
    private TreeSet<OptionDto> requiredMe = new TreeSet<>();
    private TreeSet<OptionDto> forbiddenWith = new TreeSet<>();
    private TreeSet<TariffDto> possibleTariffsOfOption = new TreeSet<>();


}
