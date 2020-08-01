package gamma.web;


import gamma.model.binding.CustomersAddBindingModel;
import gamma.model.binding.LedgerEntityAddBindingModel;
import gamma.model.service.CustomerServiceModel;
import gamma.model.service.LedgerEntityAddServiceModel;
import gamma.model.service.UserServiceModel;
import gamma.model.view.ContragentViewModel;
import gamma.service.AccountService;
import gamma.service.ContragentService;
import gamma.service.CustomerService;
import gamma.service.LedgerEntityService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final ModelMapper modelMapper;
    private final ContragentService contragentService;
    private final AccountService accountService;
    private final LedgerEntityService ledgerEntityService;

    public CustomerController(CustomerService customerService, ModelMapper modelMapper, ContragentService contragentService, AccountService accountService, LedgerEntityService ledgerEntityService) {
        this.customerService = customerService;
        this.modelMapper = modelMapper;
        this.contragentService = contragentService;
        this.accountService = accountService;
        this.ledgerEntityService = ledgerEntityService;
    }

    @GetMapping("/list")
    public ModelAndView customers(@AuthenticationPrincipal Principal principal){
        ModelAndView mav= new ModelAndView("customers");
        List<ContragentViewModel> customersViewModels = this.contragentService.findAllByType("customer").stream().
                map(contragentServiceModel1 -> modelMapper.map(contragentServiceModel1, ContragentViewModel.class) )
                .collect(Collectors.toList());
        mav.addObject("listOfcustomers", customersViewModels);
        mav.addObject("user", principal);
        return mav;
    }

    @GetMapping("/new")
    public ModelAndView newCustomers(@Valid @ModelAttribute("customersAddBindingModel") CustomersAddBindingModel customersAddBindingModel,
                                   BindingResult bindingResult){
        ModelAndView mav= new ModelAndView("new-customer");
        mav.addObject("customersAddBindingModel",customersAddBindingModel);
        return mav;
    }

    @PostMapping("/new")
    public ModelAndView newCustomersPost(@Valid @ModelAttribute("customersAddBindingModel") CustomersAddBindingModel customersAddBindingModel,
                                       BindingResult bindingResult, ModelAndView mav, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("customersAddBindingModel", customersAddBindingModel);
            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "customersAddBindingModel",
                    bindingResult);
            mav.setViewName("redirect:/customers/new");
        }else {
                CustomerServiceModel customerServiceModel =
                        this.customerService.addCustomers(this.modelMapper.map(customersAddBindingModel, CustomerServiceModel.class));
                mav.setViewName("redirect:/customers/list");
        }
        return mav;
    }

    @GetMapping("/invoice")
    public ModelAndView ledgerEntity(@Valid @ModelAttribute("ledgerEntityAddBindingModel") LedgerEntityAddBindingModel ledgerEntityAddBindingModel,
                                BindingResult bindingResult,@AuthenticationPrincipal Principal principal) {
        ModelAndView mav= new ModelAndView("sale-invoice");
        mav.addObject("accountsName", this.accountService.name());
        mav.addObject("contragentsName", this.contragentService.customers());
        mav.addObject("ledgerEntityAddBindingModel",ledgerEntityAddBindingModel);
        mav.addObject("user", principal);
        return mav;
    }

    @PostMapping("/invoice")
    public ModelAndView ledgerEntityPost(@Valid @ModelAttribute("ledgerEntityAddBindingModel")LedgerEntityAddBindingModel ledgerEntityAddBindingModel,
                                         BindingResult bindingResult, RedirectAttributes redirectAttributes,ModelAndView mav,
                                         @AuthenticationPrincipal Principal principal) {
        ledgerEntityAddBindingModel.setUser(principal.getName());
        if (bindingResult.hasErrors()|| ledgerEntityAddBindingModel.getCredit().size()==0||ledgerEntityAddBindingModel.getDebit().size()==0
        ||ledgerEntityAddBindingModel.getDescription().size()==0) {
            redirectAttributes.addFlashAttribute("ledgerEntityAddBindingModel", ledgerEntityAddBindingModel);
            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "ledgerEntityAddBindingModel",
                    bindingResult);
            mav.setViewName("redirect:/customers/invoice");
        } else {
            boolean isInvoice = true;
            System.out.println();
            LedgerEntityAddServiceModel ledgerEntityAddServiceModel = this.ledgerEntityService.postLedgerEntity(this.modelMapper.map(ledgerEntityAddBindingModel, LedgerEntityAddServiceModel.class), isInvoice);
            mav.setViewName("redirect:/customers/invoice");
            isInvoice = false;
        }
        return mav;
    }
}
