package com.tsystems.controller;

import com.tsystems.dto.ContractDto;
import com.tsystems.services.interfaces.ContractService;
import com.tsystems.services.interfaces.CustomerService;
import com.tsystems.services.interfaces.OptionService;
import com.tsystems.services.interfaces.TariffService;
import com.tsystems.util.exceptions.WrongOptionConfigurationException;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;




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
    @Secured("ADMIN")
    @GetMapping("/customer/{id}")
    public String getById(@PathVariable("id") int id, Model model) {
        model.addAttribute("customer", customerService.loadByKey(id));
        model.addAttribute("tariff", tariffService.loadAll());
        //model.addAttribute("contract", contractService.loadAll());
        model.addAttribute("option", optionService.loadAll());

        return "showCustomer";
    }

    /*@PostMapping("/updateContract")
    public String updateContract(@ModelAttribute ContractDto contract, HttpServletRequest request) throws WrongOptionConfigurationException {
        Integer tariffId = Integer.parseInt(request.getParameter("tariff"));
        String number = request.getParameter("number");
        contract = contractService.loadByKey()
        contractService.updateContract();
        return "redirect:/contracts";
    }*/

    @GetMapping("/addContract")
    public String createContract() {
        return "createContract";
    }


    @PostMapping("/addContract")
    public String addCustomer(@ModelAttribute ContractDto contract) throws WrongOptionConfigurationException {
        contractService.add(contract);

        //String[] optionsIdStr = request.getParameterValues("options");
        //List<Integer> options;
        //options = Arrays.stream(optionsIdStr).map(Integer::parseInt).collect(Collectors.toList());
        //contract.setUsedOptions(optionService.loadByKey(options.stream().iterator().next().));

        return "redirect:/customers";
    }

}
