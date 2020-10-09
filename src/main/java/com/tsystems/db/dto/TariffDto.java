package com.tsystems.db.dto;

import com.tsystems.db.entities.Tariff;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;

import java.math.BigDecimal;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Created by nikita on 24.09.2020.
 */
@Data
public class TariffDto implements DtoMapper<Tariff>, Comparable<TariffDto> {

    private Integer id;

    private String name;

    private BigDecimal cost;

    private String description;

    private TreeSet<OptionDto> possibleOptions = new TreeSet<>();

    public TariffDto() {
    }

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
                    .collect(Collectors.toCollection(TreeSet::new));
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

