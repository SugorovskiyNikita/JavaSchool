package com.tsystems.controller;

import com.tsystems.dto.CustomerDto;
import com.tsystems.entities.Customer;
import com.tsystems.mapper.CustomerMapper;
import com.tsystems.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by nikita on 07.09.20.
 */
@RestController
@RequestMapping("/")
public class CustomerController {

    @Autowired
    private CustomerMapper mapper;
    @Autowired
    public CustomerService customerService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }

    @GetMapping("/customer/{id}")
    public String getById(@PathVariable("id") int id, Model model) {
        model.addAttribute("customer", customerService.getById(id));
        return "showCustomer";
    }

    @GetMapping("/addCustomer")
    public String creatCustomerPage() {
        return "createCustomer";
    }

    @PostMapping("/addCustomer")
    public String addCustomer(@RequestBody CustomerDto customerDto) {
        customerService.addCustomer(customerDto);
        return "redirect:/customers";
    }

    @PostMapping("/updateCustomer")
    public String updateCustomer(@RequestBody CustomerDto customerDto) {
        customerService.update(customerDto);
        return "redirect:/customers";
    }

    @GetMapping("update/{id}")
    public String update(@RequestParam int id) {
        ResponseEntity.ok(customerService.getById(id));
        return "editCustomer";
    }

    @GetMapping("delete/{id}")
    public String deleteCustomer(@RequestBody CustomerDto customerDto) {
        customerService.delete(customerDto);
        return "redirect:/customers";
    }
}
