package com.tsystems.business.controller;


import com.tsystems.db.dto.OptionDto;
import com.tsystems.business.services.interfaces.OptionService;
import com.tsystems.business.services.interfaces.TariffService;
import com.tsystems.util.exceptions.WrongOptionConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by nikita on 16.09.2020.
 */
@Controller
@RequestMapping("/")
public class OptionController {

    @Autowired
    public OptionService optionService;

    @Autowired
    public TariffService tariffService;

    @GetMapping("/admin/addOption")
    public String createOption(Model model) {
        model.addAttribute("tariff", tariffService.loadAll());
        model.addAttribute("option", optionService.loadAll());
        return "createOption";}

    @PostMapping("/admin/addOption")
    public String addOption(@RequestParam("requiredFrom") List<Integer> requiredFromId,
                            @RequestParam("forbiddenWith") List<Integer> forbiddenWithId,
                            @RequestParam("forTariffs") List<Integer> forTariffsId, HttpServletRequest request) throws WrongOptionConfigurationException {

        String name = request.getParameter("name");
        String cost = request.getParameter("cost");
        String connectCost = request.getParameter("connectCost");
        String desc = request.getParameter("description");

        OptionDto newOption = new OptionDto();
        newOption.setName(name);
        newOption.setCost(new BigDecimal(cost));
        newOption.setConnectCost(new BigDecimal(connectCost));
        newOption.setDescription(desc);

        /*if (requiredFromId == null) { requiredFromId = new ArrayList<>();
        }
        if (forbiddenWithId == null) { forbiddenWithId = new ArrayList<>();
        }*/


        optionService.addNew(newOption, requiredFromId, forbiddenWithId, forTariffsId);
        return "redirect:/admin/options";
    }

    @GetMapping("/admin/options")
    public String getAllOptions(Model model){
        model.addAttribute("options", optionService.loadAll());
        return "optionsList";
    }

    @GetMapping("/admin/deleteOption/{id}")
    public String deleteOption(@PathVariable("id") int key) {
        optionService.remove(key);
        return "redirect:/admin/options";
    }

}
