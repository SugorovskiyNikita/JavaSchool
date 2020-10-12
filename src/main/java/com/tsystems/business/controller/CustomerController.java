package com.tsystems.business.controller;

import com.tsystems.db.dao.interfaces.OptionDao;
import com.tsystems.db.dto.*;
import com.tsystems.business.services.interfaces.*;
import com.tsystems.util.exceptions.WrongOptionConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * Created by nikita on 07.09.20.
 */
@Controller
@RequestMapping("/")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TariffService tariffService;

    @Autowired
    private ContractService contractService;

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @Autowired
    private OptionService optionService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private OptionDao optionDao;

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    } //временно для удобства

    @GetMapping("/login")
    public String login() {
        return "login"; }


        @GetMapping("/admin/customers")
        public String getAllCustomers(Model model) {
        model.addAttribute("customers", customerService.loadAll());
        return "customersList";
    }

    @PostMapping("/addCustomer")
    public String addCustomer(@ModelAttribute CustomerDto customer) throws WrongOptionConfigurationException {
        customerService.add(customer);
        return "redirect:/admin/customers";
    }


    @GetMapping("update/{id}")
    public String update(@PathVariable("id") int id, Model model) {
        model.addAttribute("customer", customerService.loadByKey(id));
        return "editCustomer";
    }

    @GetMapping("delete/{id}")
    public String deleteCustomer(@PathVariable("id") int key, Model model) {
        model.addAttribute("customer", customerService.loadByKey(key));
        return "redirect:/admin/customers";
    }

    @GetMapping("/customer")
    public String loadCustomer( Model model, Authentication authentication) throws Exception {
        CustomerDto customer = customerService.findByEmail(authentication.getName());
        model.addAttribute("customer", customer);
        return "profile";
    }

    @GetMapping("/welcome")
    public String welcome( Model model) throws Exception {
        Authentication authentication = authenticationFacade.getAuthentication();
        CustomerDto customer = customerService.findByEmail(authentication.getName());
        String roles = authentication.getAuthorities().toString();
        String roleName = roles.substring(1, roles.length()-1);
        RoleDto role = roleService.findByName(roleName);
        model.addAttribute("customer", customer);
        model.addAttribute("role", role);
        return "welcome";
    }


    @PostMapping("/changeTariff")
    public String changeTariff(Model model, HttpServletRequest request) throws Exception {
        Integer contractId = Integer.parseInt(request.getParameter("contractId"));
        Authentication authentication = authenticationFacade.getAuthentication();
        CustomerDto customer = customerService.findByEmail(authentication.getName());
        ContractDto contract = contractService.loadByKey(contractId);
        model.addAttribute("tariffs", tariffService.loadAll());
        model.addAttribute("customer", customer);
        model.addAttribute("contract", contract);
        return "changeTariff";
    }

    @PostMapping("/changeOption")
    public String changeOption(Model model, HttpServletRequest request) throws Exception {

        Integer contractId = Integer.parseInt(request.getParameter("contractId"));
        Integer tariffId = Integer.parseInt(request.getParameter("tariffId"));
        ContractDto contract = contractService.loadByKey(contractId);
        TariffDto tariff = tariffService.loadByKey(tariffId);
        List<OptionDto> options = optionService.getOptionsOfTariffs(tariffId);
        model.addAttribute("tariff", tariff);
        model.addAttribute("contract", contract);
        model.addAttribute("options", options);
        return "changeOption";
    }

    @PostMapping("/changeOnlyOption")
    public String changeOnlyOption(@RequestParam("contractId") Integer contractId,
                                   @RequestParam("tariffId") Integer tariffId, Model model) throws Exception {
        ContractDto contract = contractService.loadByKey(contractId);
        model.addAttribute("tariff", tariffService.loadByKey(tariffId));
        model.addAttribute("contract", contract);
        model.addAttribute("options", optionService.getOptionsOfTariffs(tariffId));
        model.addAttribute("used", contract.getUsedOptions());
        return "changeOnlyOption";
    }

    @PostMapping("/admin/blockCustomer")
    public String blockAdminCustomer( HttpServletRequest request) {
        Integer id = Integer.parseInt(request.getParameter("customerId"));
        CustomerDto customer = customerService.loadByKey(id);
        int blockLevel;
        if (request.isUserInRole("ROLE_ADMIN")) { // Check with block level must be set
            blockLevel = 2; // Blocked by T-mobile
        } else {
            blockLevel = 1; // Blocked by user
        }
        CustomerDto entity = customerService.setBlock(id, blockLevel);

        return "redirect:/admin/customerInfo/" + customer.getId();
    }

    @PostMapping("/admin/unblockCustomer")
    public String unblockAdminCustomer( HttpServletRequest request) {
        Integer id = Integer.parseInt(request.getParameter("customerId"));
        CustomerDto customer = customerService.loadByKey(id);
        CustomerDto customerDto = customerService.loadByKey(id);
        if (!request.isUserInRole("ROLE_ADMIN") && customerDto.getIsBlocked() == 2)
            return "redirect:/customer";
        CustomerDto entity = customerService.setBlock(id, 0);
        return "redirect:/admin/customerInfo/" + customer.getId();
    }


}