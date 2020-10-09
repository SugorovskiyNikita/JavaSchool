package com.tsystems.db.dto;


import com.tsystems.db.entities.Customer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.apache.commons.lang3.ObjectUtils;


import java.util.Date;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Created by nikita on 24.09.2020.
 */
@Data
public class CustomerDto implements DtoMapper<Customer>, Comparable<CustomerDto> {

    private Integer id;

    private String name;

    private String surname;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dateOfBirth;

    private String passportNumber;

    private String passportData;

    private String address;

    private String password;

    private String email;

    private Integer isBlocked;

    private TreeSet<ContractDto> contracts = new TreeSet<>();

    private TreeSet<RoleDto> roles = new TreeSet<>();

    public CustomerDto(){
        /*getId();
        getEmail();
        getName();
        getSurname();
        getIsBlocked(); */

    }

    /**
     * Create dto object from entity
     * @param customer entity to convert
     */
    public CustomerDto(Customer customer) {
        convertToDto(customer);
    }

    @Override
    public void convertToDto(Customer entity) {
        if (entity == null)
            return;
        this.id = entity.getId();
        this.name = entity.getName();
        this.surname = entity.getSurname();
        this.dateOfBirth = entity.getDateOfBirth();
        this.passportNumber = entity.getPassportNumber();
        this.passportData = entity.getPassportData();
        this.address = entity.getAddress();
        this.email = entity.getEmail();
        this.isBlocked = entity.getIsBlocked();
        this.password = entity.getPassword();
    }

    @Override
    public Customer convertToEntity() {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(name);
        customer.setSurname(surname);
        customer.setEmail(email);
        customer.setIsBlocked(isBlocked);
        customer.setPassportNumber(passportNumber);
        customer.setPassportData(passportData);
        customer.setDateOfBirth(dateOfBirth);
        customer.setAddress(address);
        customer.setPassword(password);
        if (roles != null)
            customer.setRoles(roles.stream().map(RoleDto::convertToEntity).collect(Collectors.toSet()));
        if (contracts != null)
            customer.setContracts(contracts.stream().map(ContractDto::convertToEntity).collect(Collectors.toSet()));
        return customer;
    }

    @Override
    public CustomerDto addDependencies(Customer entity) {
        if (entity != null && entity.getContracts() != null)
            this.contracts = entity.getContracts().stream()
                    .map(e -> new ContractDto(e).addDependencies(e))
                    .collect(Collectors.toCollection(TreeSet::new));
        return this;
    }

    @Override
    public int compareTo(CustomerDto o) {
        return ObjectUtils.compare(this.id, o.getId());
    }
}
