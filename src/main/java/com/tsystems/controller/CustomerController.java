package com.tsystems.controller;

import com.tsystems.entities.Customer;
import com.tsystems.services.interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by nikita on 07.09.20.
 */
@Controller
@RequestMapping("/")
public class CustomerController {

    @Autowired
    public CustomerService customerService;

    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/customer/{id}")
    public String getById(@PathVariable("id") int id, Model model) {
        model.addAttribute("customer", customerService.loadByKey(id));
        return "showCustomer";
    }

    @GetMapping("/addCustomer")
    public String creatCustomerPage() {
        return "createCustomer";
    }

    @PostMapping("/addCustomer")
    public String addCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.add(customer);
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
