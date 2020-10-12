package com.tsystems.db.dto;

import com.tsystems.db.entities.Tariff;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Created by nikita on 24.09.2020.
 */
@Data
@NoArgsConstructor
public class TariffDto implements DtoMapper<Tariff>, Comparable<TariffDto> {

    private Integer id;

    @Size(min = 2, max = 45)
    @NotNull
    private String name;

    @Digits(integer = 8, fraction = 2)
    @NotNull
    private BigDecimal cost;

    @Size(max = 255)
    private String description;

    private Set<OptionDto> possibleOptions = new HashSet<>();


    /**
     * Create dto object from entity
     * @param tariff entity to convert
     */
    public TariffDto(Tariff tariff) {
        convertToDto(tariff);
    }

    @Override
    public TariffDto addDependencies(Tariff tariff) {
        if (tariff != null && tariff.getPossibleOptions() != null)
            this.possibleOptions = tariff.getPossibleOptions().stream()
                    .map(OptionDto::new)
                    .collect(Collectors.toCollection(HashSet::new));
        return this;
    }

    @Override
    public Tariff convertToEntity() {
        Tariff tariff = new Tariff(id, name, cost, description);
        if (possibleOptions != null)
            tariff.setPossibleOptions(possibleOptions.stream().map(OptionDto::convertToEntity).collect(Collectors.toSet()));
        return tariff;
    }

    @Override
    public void convertToDto(Tariff entity) {
        if (entity == null)
            return;
        this.id = entity.getId();
        this.name = entity.getName();
        this.cost = entity.getCost();
        this.description = entity.getDescription();
    }

    @Override
    public int compareTo(TariffDto o) {
        return ObjectUtils.compare(this.id, o.getId());
    }

}

