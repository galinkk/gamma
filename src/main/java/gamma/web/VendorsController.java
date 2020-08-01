package gamma.web;


import gamma.model.binding.LedgerEntityAddBindingModel;
import gamma.model.binding.VendorsAddBindingModel;


import gamma.model.service.CustomerServiceModel;
import gamma.model.service.LedgerEntityAddServiceModel;
import gamma.model.service.VendorServiceModel;
import gamma.model.view.ContragentViewModel;
import gamma.service.AccountService;
import gamma.service.ContragentService;
import gamma.service.LedgerEntityService;
import gamma.service.VendorService;
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
import java.util.*;

import java.security.Principal;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/vendors")
public class VendorsController {

    private final VendorService vendorService;
    private final ModelMapper modelMapper;
    private final LedgerEntityService ledgerEntityService;
    private final AccountService accountService;
    private final ContragentService contragentService;

    public VendorsController(VendorService vendorService, ModelMapper modelMapper, LedgerEntityService ledgerEntityService, AccountService accountService, ContragentService contragentService) {
        this.vendorService = vendorService;
        this.modelMapper = modelMapper;
        this.ledgerEntityService = ledgerEntityService;
        this.accountService = accountService;
        this.contragentService = contragentService;
    }

    @GetMapping("/list")
    public ModelAndView vendors(@AuthenticationPrincipal Principal principal){
        ModelAndView mav= new ModelAndView("vendors");
        List<ContragentViewModel> vendorsViewModels = this.contragentService.findAllByType("vendors").stream().
                map(contragentServiceModel1 -> modelMapper.map(contragentServiceModel1, ContragentViewModel.class) )
                .collect(Collectors.toList());
        mav.addObject("listOfVendors", vendorsViewModels);
        mav.addObject("user", principal);
        return mav;
    }

    @GetMapping("/new")
    public ModelAndView newVendors(@Valid @ModelAttribute("vendorsAddBindingModel")VendorsAddBindingModel vendorsAddBindingModel,
                                   BindingResult bindingResult){
        ModelAndView mav= new ModelAndView("new-vendor");
        mav.addObject("vendorsAddBindingModel",vendorsAddBindingModel);
        return mav;
    }

    @PostMapping("/new")
    public ModelAndView newVendorsPost(@Valid @ModelAttribute("vendorsAddBindingModel")VendorsAddBindingModel vendorsAddBindingModel,
                                       BindingResult bindingResult, ModelAndView mav, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("vendorsAddBindingModel", vendorsAddBindingModel);
            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "vendorsAddBindingModel",
                    bindingResult);
            mav.setViewName("redirect:/vendors/new");
        }else {
            VendorServiceModel vendorServiceModel =
                    this.vendorService.addVendors(this.modelMapper.map(vendorsAddBindingModel,VendorServiceModel.class));
            mav.setViewName("redirect:/vendors/list");
        }
        return mav;
    }

    @GetMapping("/invoice")
    public ModelAndView ledgerEntity(@Valid @ModelAttribute("ledgerEntityAddBindingModel") LedgerEntityAddBindingModel ledgerEntityAddBindingModel,
                                     BindingResult bindingResult, @AuthenticationPrincipal Principal principal) {
        ModelAndView mav= new ModelAndView("purchase-invoice");
        mav.addObject("accountsName", this.accountService.name());
        mav.addObject("contragentsName", this.contragentService.vendors());
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
            mav.setViewName("redirect:/vendors/invoice");
        } else {
            boolean isInvoice = true;
            LedgerEntityAddServiceModel ledgerEntityAddServiceModel = this.ledgerEntityService.postLedgerEntity(this.modelMapper.map(ledgerEntityAddBindingModel, LedgerEntityAddServiceModel.class), isInvoice);
            mav.setViewName("redirect:/vendors/invoice");
            isInvoice = false;
        }
        return mav;
    }
}
