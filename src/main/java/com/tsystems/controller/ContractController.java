package com.tsystems.controller;

import com.tsystems.dto.CustomerDto;
import com.tsystems.entities.Contract;
import com.tsystems.entities.Customer;
import com.tsystems.entities.Tariff;
import com.tsystems.services.interfaces.ContractService;
import com.tsystems.services.interfaces.CustomerService;
import com.tsystems.services.interfaces.OptionService;
import com.tsystems.services.interfaces.TariffService;
import org.modelmapper.ModelMapper;
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
    public ModelMapper mapper;
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
    public String addCustomer(@ModelAttribute("contract") CustomerDto customer, HttpServletRequest request) {
        Integer tariffId = Integer.parseInt(request.getParameter("tariff"));
        Tariff tariff = tariffService.loadByKey(tariffId);
        String number = request.getParameter("number");
        Contract contract = new Contract();
        contract.setNumber(number);
        contract.setTariff(tariff);
        contract.setIsBlocked(0);
        contract.setBalance(new BigDecimal(100));
        //String[] optionsIdStr = request.getParameterValues("options");
        //List<Integer> options;
        //options = Arrays.stream(optionsIdStr).map(Integer::parseInt).collect(Collectors.toList());
        //contract.setUsedOptions(optionService.loadByKey(options.stream().iterator().next().));
        customerService.add(customer);
        contract.setCustomer(mapper.map(customer, Customer.class));
        contractService.add(contract);

        return "redirect:/customers";
    }


}
