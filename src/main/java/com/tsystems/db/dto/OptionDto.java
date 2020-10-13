package com.tsystems.db.dto;

import com.tsystems.db.entities.Option;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import java.util.stream.Collectors;

/**
 * Created by nikita on 24.09.2020.
 */
@Data
@NoArgsConstructor
public class OptionDto implements DtoMapper<Option>, Comparable<OptionDto> {

    private Integer id;

    @NotNull
    @Size(min = 2, max = 45)
    private String name;

    @Digits(integer = 8, fraction = 2)
    @NotNull
    private BigDecimal cost;

    @Digits(integer = 8, fraction = 2)
    @NotNull
    private BigDecimal connectCost;

    @Size(max = 255)
    private String description;

    private Set<OptionDto> requiredFrom = new HashSet<>();
    private Set<OptionDto> requiredMe = new HashSet<>();
    private Set<OptionDto> forbiddenWith = new HashSet<>();
    private Set<TariffDto> possibleTariffsOfOption = new HashSet<>();


    /**
     * Create dto object from entity
     *
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OptionDto optionDto = (OptionDto) o;

        if (id != null ? !id.equals(optionDto.id) : optionDto.id != null) return false;
        if (name != null ? !name.equals(optionDto.name) : optionDto.name != null) return false;
        if (cost != null ? !cost.equals(optionDto.cost) : optionDto.cost != null) return false;
        if (connectCost != null ? !connectCost.equals(optionDto.connectCost) : optionDto.connectCost != null)
            return false;
        return description != null ? description.equals(optionDto.description) : optionDto.description == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        result = 31 * result + (connectCost != null ? connectCost.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
