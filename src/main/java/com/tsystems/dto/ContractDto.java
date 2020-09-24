package com.tsystems.dto;

import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.TreeSet;
/**
        * Created by nikita on 24.09.2020.
        */
@Data
public class ContractDto {

    private Integer id;

    private String number;

    private Integer isBlocked;

    private CustomerDto customer;

    private TariffDto tariff;

    private BigDecimal balance;

    private TreeSet<OptionDto> usedOption = new TreeSet<>();
}
