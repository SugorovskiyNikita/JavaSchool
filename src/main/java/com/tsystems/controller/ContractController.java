package com.tsystems.controller;

import com.tsystems.entities.Contract;
import com.tsystems.entities.Customer;
import com.tsystems.entities.Tariff;
import com.tsystems.services.interfaces.ContractService;
import com.tsystems.services.interfaces.CustomerService;
import com.tsystems.services.interfaces.OptionService;
import com.tsystems.services.interfaces.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;


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
    public String creatCustomerPage( Model model) {
        model.addAttribute("tariff", tariffService.loadAll());
        model.addAttribute("option", optionService.loadAll());
        return "createCustomer";
    }
    @GetMapping("/addContract")
    public String createContract() { return "createContract"; }

    @Secured("ADMIN")
    @PostMapping("/addCustomer")
    public String addCustomer(@ModelAttribute("contract") Customer customer, HttpServletRequest request) {
        Integer tariffId = Integer.parseInt(request.getParameter("tariff"));
        Tariff tariff = tariffService.loadByKey(tariffId);
        String number = request.getParameter("number");
        Contract contract = new Contract();
        contract.setNumber(number);
        contract.setTariff(tariff);
        contract.setIsBlocked(0);
        contract.setBalance(new BigDecimal(100));
        customerService.add(customer);
        contract.setCustomer(customer);
        contractService.add(contract);

        return "redirect:/customers";
    }


}
