package com.tsystems.business.controller;

import com.tsystems.db.dto.*;
import com.tsystems.business.services.interfaces.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * Created by nikita on 07.09.20.
 */
@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    private final TariffService tariffService;

    private final ContractService contractService;

    private final IAuthenticationFacade authenticationFacade;

    private final OptionService optionService;

    private final RoleService roleService;

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    } //временно для удобства

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/admin/customers")
    public String getAllCustomers(Model model) {
        model.addAttribute("customers", customerService.loadAll());
        return "customersList";
    }

    @PostMapping("/addCustomer")
    public String addCustomer(@ModelAttribute CustomerDto customer) {
        customerService.add(customer);
        return "redirect:/admin/customers";
    }


    @GetMapping("update/{id}")
    public String update(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("customer", customerService.loadByKey(id));
        return "editCustomer";
    }

    @GetMapping("delete/{id}")
    public String deleteCustomer(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("customer", customerService.loadByKey(id));
        return "redirect:/admin/customers";
    }

    @GetMapping("/customer")
    public String loadCustomer(Model model, Authentication authentication) throws Exception {
        model.addAttribute("customer", customerService.findByEmail(authentication.getName()));
        return "profile";
    }

    @GetMapping("/welcome")
    public String welcome(Model model) throws Exception {
        Authentication authentication = authenticationFacade.getAuthentication();
        String roles = authentication.getAuthorities().toString();
        String roleName = roles.substring(1, roles.length() - 1);
        model.addAttribute("customer", customerService.findByEmail(authentication.getName()));
        model.addAttribute("role", roleService.findByName(roleName));
        return "welcome";
    }


    @PostMapping("/changeTariff")
    public String changeTariff(@RequestParam("contractId") Integer contractId,
                               Model model) throws Exception {
        Authentication authentication = authenticationFacade.getAuthentication();
        model.addAttribute("tariffs", tariffService.loadAll());
        model.addAttribute("customer", customerService.findByEmail(authentication.getName()));
        model.addAttribute("contract", contractService.loadByKey(contractId));
        return "changeTariff";
    }

    @PostMapping("/changeOption")
    public String changeOption(@RequestParam("contractId") Integer contractId,
                               @RequestParam("tariffId") Integer tariffId,
                               Model model) {
        model.addAttribute("tariff", tariffService.loadByKey(tariffId));
        model.addAttribute("contract", contractService.loadByKey(contractId));
        model.addAttribute("options", optionService.getOptionsOfTariff(tariffId));
        return "changeOption";
    }

    @PostMapping("/changeOnlyOptions")
    public String changeOnlyOption(@RequestParam("contractId") Integer contractId,
                                   @RequestParam("tariffId") Integer tariffId, Model model) {
        ContractDto contract = contractService.loadByKey(contractId);
        model.addAttribute("tariff", tariffService.loadByKey(tariffId));
        model.addAttribute("contract", contract);
        model.addAttribute("options", optionService.getOptionsOfTariff(tariffId));
        model.addAttribute("used", contract.getUsedOptions());
        return "changeOnlyOptions";
    }
}