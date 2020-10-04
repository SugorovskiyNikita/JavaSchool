package com.tsystems.controller;


import com.tsystems.dto.OptionDto;
import com.tsystems.entities.Option;
import com.tsystems.services.interfaces.OptionService;
import com.tsystems.util.exceptions.WrongOptionConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by nikita on 16.09.2020.
 */
@Controller
@RequestMapping("/")
public class OptionController {

    @Autowired
    public OptionService optionService;

    @GetMapping("/addOption")
    public String createOption() {return "createOption";}

    @PostMapping("/addOption")
    public String addOption(@ModelAttribute("option") OptionDto option) throws WrongOptionConfigurationException {
        optionService.add(option);
        return "redirect:/options";
    }

    @GetMapping("/options")
    public String getAllOptions(Model model){
        model.addAttribute("options", optionService.loadAll());
        return "optionsList";
    }

}
