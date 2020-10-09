package com.tsystems.bussiness.controller;

import com.tsystems.db.dto.ContractDto;
import com.tsystems.db.dto.CustomerDto;
import com.tsystems.bussiness.services.implementations.AuthenticationFacade;
import com.tsystems.bussiness.services.interfaces.ContractService;
import com.tsystems.bussiness.services.interfaces.CustomerService;
import com.tsystems.bussiness.services.interfaces.OptionService;
import com.tsystems.bussiness.services.interfaces.TariffService;
import com.tsystems.db.dto.OptionDto;
import com.tsystems.util.exceptions.ResourceNotFoundException;
import com.tsystems.util.exceptions.WrongOptionConfigurationException;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;


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

    @GetMapping("/admin/addCustomer")
    public String creatCustomerPage() {
        return "createCustomer";
    }

    @GetMapping("admin/customer/{id}")
    public String getById(@PathVariable("id") int id, Model model) {
        model.addAttribute("customer", customerService.loadByKey(id));
        model.addAttribute("tariff", tariffService.loadAll());
        model.addAttribute("option", optionService.loadAll());

        return "showCustomer";
    }

    @PostMapping("/admin/updateContract")
    public String updateContract(@RequestParam("tariff") Integer tariffId,
                              @RequestParam("option") List<Integer> options,
                              @RequestParam("contract") Integer id, @RequestParam("number") String number){

        ContractDto entity = contractService.updateContract(id, tariffId, options, number);

        return "customerContracts";
    }


    @GetMapping("/admin/addContract")
    public String createContract() {
        return "createContract";
    }


    @PostMapping("/admin/addContract")
    public String addCustomer(@ModelAttribute ContractDto contract) throws WrongOptionConfigurationException {
        contractService.add(contract);
        return "redirect:/customers";
    }

    @GetMapping("/admin/contracts")
    public String getAllContracts(Model model) {
        model.addAttribute("contracts", contractService.loadAll());
        model.addAttribute("customer", customerService.loadAll());
        return "contractsList";
    }

    /*@GetMapping("/contractsCustomer")
    public String getAllContractsCustomer(Model model) throws Exception {
        Authentication authentication = authenticationFacade.getAuthentication();
        CustomerDto customer = customerService.findByEmail(authentication.getName());
        model.addAttribute("customer", customer);
        return "customerContracts";
    }*/



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
            return "redirect:/customer";

        ContractDto entity = contractService.setBlock(id, 0);
        if (entity.getId() == null) {
            throw new ResourceNotFoundException("contract", id);
        }
        return "redirect:/customer";
    }

    @PostMapping("/viewContract")
    public String viewContractByCustomer(Model model, HttpServletRequest request) {
        Integer contractId = Integer.parseInt(request.getParameter("contractId"));
        ContractDto contract = contractService.loadByKey(contractId);
        model.addAttribute("contract", contract);
        Set<OptionDto> optionsList = contract.getUsedOptions();
        model.addAttribute("optionsList", optionsList);

        return "customerContracts";
    }

    @GetMapping("/viewContract")
    public String viewContractByCustomer() {
        return "customerContracts";
    }



    /*@PostMapping("/viewContracts")
    public String getAllContractsCustomer(Model model, HttpServletRequest request) throws Exception {
        Authentication authentication = authenticationFacade.getAuthentication();
        CustomerDto customer = customerService.findByEmail(authentication.getName());
        model.addAttribute("customer", customer);
        Integer contractId = Integer.parseInt(request.getParameter("contractId"));
        ContractDto contract = contractService.loadByKey(contractId);
        model.addAttribute("contract", contract);
        Set<OptionDto> optionsList = contract.getUsedOptions();
        model.addAttribute("optionsList", optionsList);
        return "customerContracts";
    }*/

}
