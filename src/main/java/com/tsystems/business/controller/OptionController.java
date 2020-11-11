package com.tsystems.business.controller;


import com.tsystems.db.dto.OptionDto;
import com.tsystems.business.services.interfaces.OptionService;
import com.tsystems.business.services.interfaces.TariffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by nikita on 16.09.2020.
 */
@Controller
@RequiredArgsConstructor
public class OptionController {

    public final OptionService optionService;

    public final TariffService tariffService;

    @GetMapping("/admin/addOption")
    public String createOption(Model model) {
        model.addAttribute("tariff", tariffService.loadAll());
        model.addAttribute("option", optionService.loadAll());
        return "createOption";
    }

    @PostMapping("/admin/addOption")
    public String addOption(@RequestParam("forTariffs") List<Integer> forTariffsId,
                            @RequestParam("name") String name,
                            @RequestParam("cost") Integer cost,
                            @RequestParam("connectCost") Integer connectCost,
                            @RequestParam("description") String desc,
                            HttpServletRequest request)  {

        String[] requiredFromId;
        String[] forbiddenWithId;
        if ((requiredFromId = request.getParameterValues("requiredFrom")) == null)
            requiredFromId = new String[0];
        if ((forbiddenWithId = request.getParameterValues("forbiddenWith")) == null)
            forbiddenWithId = new String[0];


        OptionDto newOption = new OptionDto();
        newOption.setName(name);
        newOption.setCost(new BigDecimal(cost));
        newOption.setConnectCost(new BigDecimal(connectCost));
        newOption.setDescription(desc);

        optionService.addNew(newOption, requiredFromId, forbiddenWithId, forTariffsId);
        return "redirect:/admin/options";
    }

    @GetMapping("/admin/options")
    public String getAllOptions(Model model) {
        model.addAttribute("options", optionService.loadAll());
        return "optionsList";
    }

    @GetMapping("/admin/deleteOption/{id}")
    public String deleteOption(@PathVariable("id") Integer id) throws NamingException {
        optionService.remove(id);
        return "redirect:/admin/options";
    }

}
