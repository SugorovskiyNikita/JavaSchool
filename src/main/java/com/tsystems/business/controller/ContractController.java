package com.tsystems.business.controller;

import com.tsystems.db.dto.ContractDto;
import com.tsystems.db.dto.CustomerDto;
import com.tsystems.business.services.implementations.AuthenticationFacade;
import com.tsystems.business.services.interfaces.ContractService;
import com.tsystems.business.services.interfaces.CustomerService;
import com.tsystems.business.services.interfaces.OptionService;
import com.tsystems.business.services.interfaces.TariffService;
import com.tsystems.db.dto.TariffDto;
import com.tsystems.util.variable.Variable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * Created by nikita on 20.09.2020.
 */
@Controller
@RequiredArgsConstructor
public class ContractController extends Variable {

    public final ContractService contractService;

    public final CustomerService customerService;

    public final TariffService tariffService;

    public final OptionService optionService;

    public final AuthenticationFacade authenticationFacade;

    @GetMapping("/admin/addCustomer")
    public String creatCustomerPage() {
        return "createCustomer";
    }

    @GetMapping("/admin/customer/{id}")
    public String chooseTariffAndOption(@PathVariable("id") Integer id, Model model) {
        model.addAttribute(CUSTOMER_STR, customerService.loadByKey(id));
        model.addAttribute(TARIFF_STR, tariffService.loadAll());
        model.addAttribute("options", optionService.loadAll());
        return "showCustomer";
    }

    @GetMapping("/admin/changeOption/{id}")
    public String chooseTariffAndOptionAdmin(@PathVariable("id") Integer id, Model model) {
        ContractDto contract = contractService.loadByKey(id);
        TariffDto tariff = tariffService.loadByKey(contract.getTariff().getId());
        model.addAttribute("contract", contractService.loadByKey(id));
        model.addAttribute("options", optionService.getOptionsOfTariff(tariff.getId()));
        model.addAttribute("used", contract.getUsedOptions());
        model.addAttribute(TARIFF_STR, contract.getTariff());
        return "changeOptionsAdmin";
    }

    @GetMapping("/admin/customerInfo/{id}")
    public String getById(@PathVariable("id") Integer id, Model model) {
        model.addAttribute(CUSTOMER_STR, customerService.loadByKey(id));
        model.addAttribute(TARIFF_STR, tariffService.loadAll());
        model.addAttribute("option", optionService.loadAll());
        return "customerInfo";
    }

    @PostMapping("/admin/updateContract")
    public String updateContract(@RequestParam("tariffId") Integer tariffId,
                                 @RequestParam("options") List<Integer> options,
                                 @RequestParam("contractId") Integer id,
                                 @RequestParam("number") String number) {
        CustomerDto customer = customerService.loadByKey(contractService.loadByKey(id).getCustomer().getId());
        contractService.updateContract(id, tariffId, options, number);
        return CUSTOMER_INFO + customer.getId();

    }

    @PostMapping("/updateContractCustomer")
    public String updateContractCustomer(@RequestParam("tariffId") Integer tariffId,
                                         @RequestParam("options") List<Integer> options,
                                         @RequestParam("contractId") Integer id,
                                         @RequestParam("number") String number) {
        contractService.updateContract(id, tariffId, options, number);
        return REDIRECT_CUSTOMER;
    }


    @GetMapping("/admin/addContract")
    public String createContract() {
        return "customerInfo";
    }


    @PostMapping("/admin/addContractForCustomer")
    public String addContractForCustomer(@RequestParam("customerId") Integer customerId) {
        contractService.addNew(customerId);
        return CUSTOMER_INFO + customerId;
    }

    @GetMapping("/admin/contracts")
    public String getAllContracts(Model model) {
        model.addAttribute("contracts", contractService.loadAll());
        model.addAttribute(CUSTOMER_STR, customerService.loadAll());
        return "contractsList";
    }

    @PostMapping("/block")
    public String block(HttpServletRequest request) {
        Integer id = Integer.parseInt(request.getParameter(CONTRACT_ID));
        int blockLevel;
        if (request.isUserInRole(ROLE_ADMIN)) { // Check with block level must be set
            blockLevel = 2; // Blocked by T-mobile
        } else {
            blockLevel = 1; // Blocked by user
        }
        contractService.setBlock(id, blockLevel);
        return REDIRECT_CUSTOMER;
    }

    @PostMapping("/unblock")
    public String unblock(HttpServletRequest request) {
        Integer id = Integer.parseInt(request.getParameter(CONTRACT_ID));
        ContractDto contractDto = contractService.loadByKey(id);

        if (!request.isUserInRole(ROLE_ADMIN) && contractDto.getIsBlocked() == 2)
            return REDIRECT_CUSTOMER;

        contractService.setBlock(id, 0);
        return REDIRECT_CUSTOMER;
    }

    @PostMapping("/viewContract")
    public String viewContractByCustomer(Model model,
                                         @RequestParam("contractId") Integer contractId) {
        model.addAttribute("contract", contractService.loadByKey(contractId));
        model.addAttribute("optionsList", contractService.loadByKey(contractId).getUsedOptions());
        return "customerContracts";
    }

    @GetMapping("/viewContract")
    public String viewContractByCustomer() {
        return "customerContracts";
    }

    @PostMapping("/admin/block")
    public String blockAdmin(HttpServletRequest request) {
        Integer id = Integer.parseInt(request.getParameter(CONTRACT_ID));
        CustomerDto customer = customerService.loadByKey(contractService.loadByKey(id).getCustomer().getId());
        int blockLevel;
        if (request.isUserInRole(ROLE_ADMIN)) { // Check with block level must be set
            blockLevel = 2; // Blocked by T-mobile
        } else {
            blockLevel = 1; // Blocked by user
        }
        contractService.setBlock(id, blockLevel);
        return CUSTOMER_INFO + customer.getId();
    }

    @PostMapping("/admin/unblock")
    public String unblockAdmin(HttpServletRequest request) {
        Integer id = Integer.parseInt(request.getParameter(CONTRACT_ID));
        CustomerDto customer = customerService.loadByKey(contractService.loadByKey(id).getCustomer().getId());
        ContractDto contractDto = contractService.loadByKey(id);
        if (!request.isUserInRole(ROLE_ADMIN) && contractDto.getIsBlocked() == 2)
            return REDIRECT_CUSTOMER;
        contractService.setBlock(id, 0);
        return CUSTOMER_INFO + customer.getId();
    }


}
