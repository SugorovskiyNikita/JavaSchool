package com.tsystems.business.controller;

import com.tsystems.business.services.interfaces.OptionService;
import com.tsystems.db.dto.TariffDto;
import com.tsystems.business.services.interfaces.TariffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by nikita on 13.09.2020.
 */
@Controller
@RequiredArgsConstructor
public class TariffController {

    private final TariffService tariffService;

    private final OptionService optionService;

    @GetMapping("/admin/addTariff")
    public String createTariff(Model model) {
        model.addAttribute("options", optionService.loadAll());
        return "createTariff";
    }

    @PostMapping("/admin/addNewTariff")
    public String addTariff(@RequestParam("options") List<Integer> newOptions,
                            @ModelAttribute("tariff") TariffDto tariff) {
        tariffService.addNew(tariff, newOptions);
        return "redirect:/admin/tariffs";
    }

    @GetMapping("/admin/tariffs")
    public String getAllTariffs(Model model) {
        model.addAttribute("tariffs", tariffService.loadAll());
        return "tariffsList";
    }

    @GetMapping("/deleteTariff/{id}")
    public String deleteTariff(@PathVariable("id") Integer key) {
        tariffService.remove(key);
        return "redirect:/admin/tariffs";
    }

    @GetMapping("/admin/editTariff/{id}")
    public String editTariff(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("tariff", tariffService.loadByKey(id));
        model.addAttribute("options", optionService.loadAll());
        model.addAttribute("possible", optionService.getOptionsOfTariff(id));
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

    @GetMapping(value = "/tariffs", produces = "application/json")
    @ResponseBody
    public String getTariffsRest() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(tariffService.loadAll());
    }
}
