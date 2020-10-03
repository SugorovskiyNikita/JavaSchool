package com.tsystems.controller;

import com.tsystems.dto.CustomerDto;
import com.tsystems.entities.Contract;
import com.tsystems.entities.Customer;
import com.tsystems.entities.Role;
import com.tsystems.entities.Tariff;
import com.tsystems.services.interfaces.ContractService;
import com.tsystems.services.interfaces.CustomerService;
import com.tsystems.services.interfaces.TariffService;
import com.tsystems.util.exceptions.WrongOptionConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by nikita on 07.09.20.
 */
@Controller
@RequestMapping("/")
public class CustomerController {

    @Autowired
    public CustomerService customerService;

    @Autowired
    public TariffService tariffService;

    @Autowired
    public ContractService contractService;

    @GetMapping("/")
    public String index() {

        return "createCustomer";
    } //временно для удобства

    @GetMapping("/login")
    public String login() {
        return "login"; }

    @GetMapping("/customers")
    public String getAllCustomers(Model model) {
        model.addAttribute("customers", customerService.loadAll());
        return "customersList";
    }

    @PostMapping("/addCustomer")
    public String addCustomer(@ModelAttribute CustomerDto customer) throws WrongOptionConfigurationException {
        customerService.add(customer);

        //String[] optionsIdStr = request.getParameterValues("options");
        //List<Integer> options;
        //options = Arrays.stream(optionsIdStr).map(Integer::parseInt).collect(Collectors.toList());
        //contract.setUsedOptions(optionService.loadByKey(options.stream().iterator().next().));

        return "redirect:/customers";
    }


    @GetMapping("update/{id}")
    public String update(@PathVariable("id") int id, Model model) {
        model.addAttribute("customer", customerService.loadByKey(id));
        return "editCustomer";
    }

    @GetMapping("delete/{id}")
    public String deleteCustomer(@PathVariable("id") int key, Model model) {
        model.addAttribute("customer", customerService.loadByKey(key));
        return "redirect:/customers";
    }

}