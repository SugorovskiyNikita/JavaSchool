package com.tsystems.controller;

import com.tsystems.dao.interfaces.OptionDao;
import com.tsystems.dto.ContractDto;
import com.tsystems.dto.CustomerDto;
import com.tsystems.dto.OptionDto;
import com.tsystems.entities.Customer;
import com.tsystems.entities.Option;
import com.tsystems.security.SecurityUser;
import com.tsystems.services.interfaces.*;
import com.tsystems.util.exceptions.WrongOptionConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityGraph;
import javax.persistence.Subgraph;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.*;


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

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @Autowired
    private OptionService optionService;

    @Autowired
    private OptionDao optionDao;

    @GetMapping("/")
    public String index() {

        return "createCustomer";
    } //временно для удобства

    @GetMapping("/login")
    public String login() {
        return "login"; }


        @GetMapping("/admin/customers")
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

    @GetMapping("/customer")
    public String loadCustomer( Model model, Authentication authentication) throws Exception {
        CustomerDto customer = customerService.findByEmail(authentication.getName());
        model.addAttribute("customer", customer);
        return "profile";
    }

    @GetMapping("/welcome")
    public String welcome( Model model) throws Exception {
        Authentication authentication = authenticationFacade.getAuthentication();
        CustomerDto customer = customerService.findByEmail(authentication.getName());
        model.addAttribute("customer", customer);
        return "welcome";
    }

    @GetMapping("/changeTariff")
    public String changeTariff(Model model) throws Exception {
        Authentication authentication = authenticationFacade.getAuthentication();
        CustomerDto customer = customerService.findByEmail(authentication.getName());
        ContractDto contract = customer.getContracts().first();
        model.addAttribute("tariffs", tariffService.loadAll());
        model.addAttribute("customer", customer);
        model.addAttribute("contract", contract);
        return "changeTariff";
    }

    @GetMapping("/changeOption")
    public String changeOption(Model model) throws Exception {
        Authentication authentication = authenticationFacade.getAuthentication();
        CustomerDto customer = customerService.findByEmail(authentication.getName());
        ContractDto contract = customer.getContracts().first();
        model.addAttribute("tariffs", tariffService.loadAll());
        model.addAttribute("customer", customer);
        model.addAttribute("contract", contract);
        model.addAttribute("option", optionService.loadAll());
        return "changeOption";
    }


}