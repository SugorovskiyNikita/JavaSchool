package com.tsystems.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by nikita on 07.09.20.
 */
@Controller
@RequestMapping("/")
public class CustomerController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
