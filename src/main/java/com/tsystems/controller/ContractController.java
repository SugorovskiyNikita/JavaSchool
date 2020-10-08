package com.tsystems.controller;

import com.tsystems.dto.ContractDto;
import com.tsystems.dto.CustomerDto;
import com.tsystems.services.implementations.AuthenticationFacade;
import com.tsystems.services.interfaces.ContractService;
import com.tsystems.services.interfaces.CustomerService;
import com.tsystems.services.interfaces.OptionService;
import com.tsystems.services.interfaces.TariffService;
import com.tsystems.util.exceptions.ResourceNotFoundException;
import com.tsystems.util.exceptions.WrongOptionConfigurationException;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;



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

    @Autowired
    public AuthenticationFacade authenticationFacade;

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

    @GetMapping("/contracts")
    public String getAllContracts(Model model) {
        model.addAttribute("contracts", contractService.loadAll());
        model.addAttribute("customer", customerService.loadAll());
        return "contractsList";
    }

    @GetMapping("/contractsCustomer")
    public String getAllContractsCustomer(Model model) throws Exception {
        Authentication authentication = authenticationFacade.getAuthentication();
        CustomerDto customer = customerService.findByEmail(authentication.getName());
        model.addAttribute("customer", customer);
        return "contracts";
    }



    @PostMapping("/block")
    public String block( HttpServletRequest request) throws ResourceNotFoundException {
        Integer id = Integer.parseInt(request.getParameter("contractId"));
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
        return "redirect:/customer";
    }

    @PostMapping("/unblock")
    public String unblock( HttpServletRequest request) throws ResourceNotFoundException {
        Integer id = Integer.parseInt(request.getParameter("contractId"));
        ContractDto contractDto = contractService.loadByKey(id);
        if (contractDto == null) {
            throw new ResourceNotFoundException("contract", id);
        }

        if (!request.isUserInRole("ROLE_ADMIN") && contractDto.getIsBlocked() == 2)
            return "";

        ContractDto entity = contractService.setBlock(id, 0);
        if (entity.getId() == null) {
            throw new ResourceNotFoundException("contract", id);
        }
        return "redirect:/customer";
    }

}
