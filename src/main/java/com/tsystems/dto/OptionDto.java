package com.tsystems.dto;

import com.tsystems.entities.Option;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;

import java.math.BigDecimal;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Created by nikita on 24.09.2020.
 */
@Data
public class OptionDto implements DtoMapper<Option>, Comparable<OptionDto> {

    private Integer id;

    private String name;

    private BigDecimal cost;

    private BigDecimal connectCost;

    private String description;

    private TreeSet<OptionDto> requiredFrom = new TreeSet<>();
    private TreeSet<OptionDto> requiredMe = new TreeSet<>();
    private TreeSet<OptionDto> forbiddenWith = new TreeSet<>();
    private TreeSet<TariffDto> possibleTariffsOfOption = new TreeSet<>();

    public OptionDto() {
    }

    /**
     * Create dto object from entity
     * @param option entity to convert
     */
    public OptionDto(Option option) {
        convertToDto(option);
    }

    @Override
    public OptionDto addDependencies(Option option) {
        if (option == null)
            return this;
        if (option.getRequired() != null)
            requiredFrom = option.getRequired().stream()
                    .map(OptionDto::new)
                    .collect(Collectors.toCollection(TreeSet::new));
        if (option.getForbidden() != null)
            forbiddenWith = option.getForbidden().stream()
                    .map(OptionDto::new)
                    .collect(Collectors.toCollection(TreeSet::new));
        if (option.getPossibleTariffsOfOption() != null)
            possibleTariffsOfOption = option.getPossibleTariffsOfOption().stream()
                    .map(TariffDto::new)
                    .collect(Collectors.toCollection(TreeSet::new));

        return this;
    }

    @Override
    public Option convertToEntity() {
        Option option = new Option(id, name, cost, connectCost, description);
        if (requiredFrom != null)
            option.setRequired(requiredFrom.stream().map(OptionDto::convertToEntity).collect(Collectors.toSet()));
        if (forbiddenWith != null)
            option.setForbidden(forbiddenWith.stream().map(OptionDto::convertToEntity).collect(Collectors.toSet()));
        if (possibleTariffsOfOption != null)
            option.setPossibleTariffsOfOption(possibleTariffsOfOption.stream().map(TariffDto::convertToEntity).collect(Collectors.toSet()));
        return option;
    }

    @Override
    public void convertToDto(Option entity) {
        if (entity == null)
            return;
        this.id = entity.getId();
        this.name = entity.getName();
        this.cost = entity.getCost();
        this.connectCost = entity.getConnectCost();
        this.description = entity.getDescription();
    }

    @Override
    public int compareTo(OptionDto o) {
        return ObjectUtils.compare(this.id, o.getId());
    }

}
