package com.tsystems.db.dto;

import com.tsystems.db.entities.Contract;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;

import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Created by nikita on 24.09.2020.
 */
@Data
@NoArgsConstructor
public class ContractDto implements DtoMapper<Contract>, Comparable<ContractDto> {

    private Integer id;

    @NotNull
    private String number;

    @Min(0)
    @Max(2)
    private Integer isBlocked;

    private CustomerDto customer;

    private TariffDto tariff;

    private BigDecimal balance;

    private TreeSet<OptionDto> usedOptions = new TreeSet<>();


    /**
     * Create dto object from entity
     * @param contract entity to convert
     */
    public ContractDto(Contract contract) {
        this.id = contract.getId();
        this.number = contract.getNumber();
        this.isBlocked = contract.getIsBlocked();
        this.balance = contract.getBalance();
        if (contract.getCustomer() != null)
            this.customer = new CustomerDto(contract.getCustomer());
        if (contract.getTariff() != null)
            this.tariff = new TariffDto(contract.getTariff());
    }

    @Override
    public Contract convertToEntity() {
        Contract contract = new Contract();
        contract.setId(id);
        contract.setNumber(number);
        contract.setIsBlocked(isBlocked);
        contract.setBalance(balance);
        if (customer != null)
            contract.setCustomer(customer.convertToEntity());
        if (tariff != null)
            contract.setTariff(tariff.convertToEntity());
        if (usedOptions != null)
            contract.setUsedOptions(usedOptions.stream().map(OptionDto::convertToEntity).collect(Collectors.toSet()));
        return contract;
    }

    @Override
    public void convertToDto(Contract entity) {
        if (entity == null)
            return;

        this.id = entity.getId();
        this.number = entity.getNumber();
        this.isBlocked = entity.getIsBlocked();
        this.balance = entity.getBalance();
        if (entity.getCustomer() != null)
            this.customer = new CustomerDto(entity.getCustomer());
        if (entity.getTariff() != null)
            this.tariff = new TariffDto(entity.getTariff());
    }

    @Override
    public ContractDto addDependencies(Contract entity) {
        if (entity != null && entity.getUsedOptions() != null)
            this.usedOptions = entity.getUsedOptions().stream()
                    .map(OptionDto::new)
                    .collect(Collectors.toCollection(TreeSet::new));
        return this;
    }

    @Override
    public int compareTo(ContractDto o) {
        return ObjectUtils.compare(this.id, o.getId());
    }


}
