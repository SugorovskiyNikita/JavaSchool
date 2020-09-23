package com.tsystems.controller;

import com.tsystems.entities.Contract;
import com.tsystems.entities.Customer;
import com.tsystems.entities.Tariff;
import com.tsystems.services.interfaces.ContractService;
import com.tsystems.services.interfaces.CustomerService;
import com.tsystems.services.interfaces.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return "index";
    }

    @GetMapping("/login")
    public String login() { return "login"; }

    @GetMapping("/customer/{id}")
    public String getById(@PathVariable("id") int id, Model model) {
        model.addAttribute("customer", customerService.loadByKey(id));
        return "showCustomer";
    }

    @GetMapping("/addCustomer")
    public String creatCustomerPage( Model model) {
        model.addAttribute("tariff", tariffService.loadAll());
        return "createCustomer";
    }

    @PostMapping("/addCustomer")
    public String addCustomer(@ModelAttribute("customer") Customer customer, HttpServletRequest request) {
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

    @GetMapping("/customers")
    public String getAllCustomers(Model model) {
        model.addAttribute("customers", customerService.loadAll());
        return "customersList";
    }

    @PostMapping("/updateCustomer")
    public String updateCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.update(customer);
        return "redirect:/customers";
    }

    @GetMapping("update/{id}")
    public String update(@PathVariable("id") int id, Model model) {
        model.addAttribute("customer", customerService.loadByKey(id));
        return "editCustomer";
    }

    @GetMapping("delete/{id}")
    public String deleteCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.remove(customer);
        return "redirect:/customers";
    }
}
