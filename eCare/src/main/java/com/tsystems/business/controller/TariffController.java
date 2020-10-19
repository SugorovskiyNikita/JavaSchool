package com.tsystems.business.controller;

import com.tsystems.business.services.interfaces.OptionService;
import com.tsystems.db.dto.TariffDto;
import com.tsystems.business.services.interfaces.TariffService;
import com.tsystems.util.exceptions.WrongOptionConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by nikita on 13.09.2020.
 */
@Controller
@RequestMapping("/")
public class TariffController {

    @Autowired
    private TariffService tariffService;

    @Autowired
    private OptionService optionService;

    @GetMapping("/admin/addTariff")
    public String createTariff(Model model) {
        model.addAttribute("options", optionService.loadAll());
        return "createTariff";
    }

    @PostMapping("/admin/addNewTariff")
    public String addTariff(@RequestParam("options") List<Integer> newOptions, @ModelAttribute("tariff") TariffDto tariff) throws WrongOptionConfigurationException {
        tariffService.addNew(tariff, newOptions);
        return "redirect:/admin/tariffs";
    }

    @GetMapping("/admin/tariffs")
    public String getAllTariffs(Model model) {
        model.addAttribute("tariffs", tariffService.loadAll());
        return "tariffsList";
    }

    @GetMapping("/deleteTariff/{id}")
    public String deleteTariff(@PathVariable("id") int key) {
        tariffService.remove(key);
        return "redirect:/admin/tariffs";
    }

    @GetMapping("/admin/editTariff/{id}")
    public String editTariff(@PathVariable("id") int id, Model model) {
        model.addAttribute("tariff", tariffService.loadByKey(id));
        model.addAttribute("options", optionService.loadAll());
        model.addAttribute("possible", optionService.getOptionsOfTariffs(id));
        return "editTariff";
    }

    @PostMapping("/admin/editTariff")
    public String editTariff(@RequestParam("tariffId") Integer id,
                             @RequestParam("options") List<Integer> newOptions,
                             @RequestParam("name") String name,
                             @RequestParam("cost") BigDecimal cost,
                             @RequestParam("description") String description) {
        tariffService.update(id, newOptions, name, cost, description);
        return "redirect:/admin/editTariff/" + id;
    }

}
