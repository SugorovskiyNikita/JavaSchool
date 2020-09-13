package com.tsystems.controller;

import com.tsystems.entities.Tariff;
import com.tsystems.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by nikita on 13.09.2020.
 */
@Controller
@RequestMapping
public class TariffController {

    @Autowired
    public TariffService tariffService;

    @GetMapping("/addTariff")
    public String createTariff() {return "createTariff";}

    @PostMapping("/addTariff")
    public String addTariff(@ModelAttribute("tariff") Tariff tariff) {
        tariffService.addTariff(tariff);
        return "redirect:/tariffs";
    }
}