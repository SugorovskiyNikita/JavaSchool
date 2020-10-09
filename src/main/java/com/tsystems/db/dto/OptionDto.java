package com.tsystems.db.dto;

import com.tsystems.db.entities.Option;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
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

    private Set<OptionDto> requiredFrom = new HashSet<>();
    private Set<OptionDto> requiredMe = new HashSet<>();
    private Set<OptionDto> forbiddenWith = new HashSet<>();
    private Set<TariffDto> possibleTariffsOfOption = new HashSet<>();

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
                    .collect(Collectors.toCollection(HashSet::new));
        if (option.getForbidden() != null)
            forbiddenWith = option.getForbidden().stream()
                    .map(OptionDto::new)
                    .collect(Collectors.toCollection(HashSet::new));
        if (option.getPossibleTariffsOfOption() != null)
            possibleTariffsOfOption = option.getPossibleTariffsOfOption().stream()
                    .map(TariffDto::new)
                    .collect(Collectors.toCollection(HashSet::new));

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
