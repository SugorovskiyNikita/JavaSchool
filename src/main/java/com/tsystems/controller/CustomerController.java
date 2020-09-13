package com.tsystems.controller;

import com.tsystems.dto.CustomerDto;

import com.tsystems.service.CustomerService;
import com.tsystems.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by nikita on 07.09.20.
 */
@RequestMapping(value = "/")
@RestController
public class CustomerController {

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
    public String getById(@RequestParam int id) {
        ResponseEntity.ok(customerService.getById(id));
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
