package com.tsystems.controller;

import com.tsystems.dto.ContractDto;
import com.tsystems.entities.Tariff;
import com.tsystems.services.interfaces.ContractService;
import com.tsystems.services.interfaces.CustomerService;
import com.tsystems.services.interfaces.OptionService;
import com.tsystems.services.interfaces.TariffService;
import com.tsystems.util.exceptions.ResourceNotFoundException;
import com.tsystems.util.exceptions.WrongOptionConfigurationException;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by nikita on 20.09.2020.
 */
@Controller
@RequestMapping("/")
public class ContractController {

    @Autowired
    public ContractService contractService;

    @Autowired
    public CustomerService customerService;

    @Autowired
    public TariffService tariffService;

    @Autowired
    public OptionService optionService;

    @GetMapping("/addCustomer")
    public String creatCustomerPage() {
        return "createCustomer";
    }

    @GetMapping("/customer/{id}")
    public String getById(@PathVariable("id") int id, Model model) {
        model.addAttribute("customer", customerService.loadByKey(id));
        model.addAttribute("tariff", tariffService.loadAll());
        model.addAttribute("option", optionService.loadAll());

        return "showCustomer";
    }

    @PostMapping("/updateContract")
    public String updateContract(@RequestParam("tariff") Integer tariffId,
                              @RequestParam("option") List<Integer> options,
                              @RequestParam("contract") Integer id, @RequestParam("number") String number){

        ContractDto entity = contractService.updateContract(id, tariffId, options, number);

        return "redirect:/contracts";
    }


    @GetMapping("/addContract")
    public String createContract() {
        return "createContract";
    }


    @PostMapping("/addContract")
    public String addCustomer(@ModelAttribute ContractDto contract) throws WrongOptionConfigurationException {
        contractService.add(contract);
        return "redirect:/customers";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/contracts")
    public String getAllContracts(Model model) {
        model.addAttribute("contracts", contractService.loadAll());
        model.addAttribute("customer", customerService.loadAll());
        return "contractsList";
    }

    @PostMapping("/{id}/block")
    @Secured("ROLE_USER")
    public void block(@PathVariable Integer id, HttpServletRequest request) throws ResourceNotFoundException {
        int blockLevel;
        if (request.isUserInRole("ROLE_ADMIN")) { // Check with block level must be set
            blockLevel = 2; // Blocked by T-mobile
        } else {
            blockLevel = 1; // Blocked by user
        }
        ContractDto entity = contractService.setBlock(id, blockLevel);
        if (entity.getId() == null) {
            throw new ResourceNotFoundException("contract", id);
        }
    }

}
