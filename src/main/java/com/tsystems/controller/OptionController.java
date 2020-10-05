package com.tsystems.controller;


import com.tsystems.dto.OptionDto;
import com.tsystems.entities.Option;
import com.tsystems.services.interfaces.OptionService;
import com.tsystems.services.interfaces.TariffService;
import com.tsystems.util.exceptions.WrongOptionConfigurationException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.RollbackException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/addOption")
    public String createOption(Model model) {
        model.addAttribute("tariff", tariffService.loadAll());
        model.addAttribute("option", optionService.loadAll());
        return "createOption";}

    @PostMapping("/addOption")
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
        return "redirect:/options";
    }

    @GetMapping("/options")
    public String getAllOptions(Model model){
        model.addAttribute("options", optionService.loadAll());
        return "optionsList";
    }

}
