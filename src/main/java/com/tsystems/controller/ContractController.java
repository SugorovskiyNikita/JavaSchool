package com.tsystems.controller;

import com.tsystems.entities.Contract;
import com.tsystems.entities.Customer;
import com.tsystems.entities.Tariff;
import com.tsystems.services.implementations.TariffServiceImpl;
import com.tsystems.services.interfaces.ContractService;
import com.tsystems.services.interfaces.CustomerService;
import com.tsystems.services.interfaces.OptionService;
import com.tsystems.services.interfaces.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/addContract")
    public String createContract() { return "createContract"; }

    @PostMapping("/addContract")
    public String addContract(Model model, HttpServletRequest req) {
        Integer customerId = Integer.valueOf(req.getParameter("customer_id"));
        String number = req.getParameter("number");
        Integer tariffId = Integer.parseInt(req.getParameter("tariff"));
        Contract contract = new Contract();
        Tariff tariff = tariffService.loadByKey(tariffId);
        Customer customer = customerService.loadByKey(customerId);
        contract.setCustomer(customer);
        contract.setTariff(tariff);
        contract.setIsBlocked(0);
        contract.setBalance(new BigDecimal(100));
        contractService.add(contract);


        return "redirect:/contractList";
    }


}
