package gamma.web;

import gamma.model.binding.LedgerEntityAddBindingModel;
import gamma.model.service.LedgerEntityAddServiceModel;
import gamma.model.view.LedgerEntityViewModel;
import gamma.service.AccountService;
import gamma.service.ContragentService;
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
@RequestMapping()
public class LedgerEntityController {

    private final AccountService accountService;
    private final ContragentService contragentService;
    private final LedgerEntityService ledgerEntityService;
    private final ModelMapper modelMapper;

    public LedgerEntityController(AccountService accountService, ContragentService contragentService, LedgerEntityService ledgerEntityService, ModelMapper modelMapper) {

        this.accountService = accountService;
        this.contragentService = contragentService;
        this.ledgerEntityService = ledgerEntityService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/tables")
    public ModelAndView ledgerEntity(@Valid @ModelAttribute("ledgerEntityAddBindingModel")LedgerEntityAddBindingModel ledgerEntityAddBindingModel,
                                     BindingResult bindingResult, @AuthenticationPrincipal Principal principal) {
        ModelAndView mav= new ModelAndView("/tables");
        mav.addObject("accountsName", this.accountService.name());
        mav.addObject("contragentsName", this.contragentService.name());
        mav.addObject("ledgerEntityAddBindingModel",ledgerEntityAddBindingModel);
        mav.addObject("user", principal);
        return mav;
    }

    @PostMapping("/tables")
    public ModelAndView ledgerEntityPost(@Valid @ModelAttribute("ledgerEntityAddBindingModel")LedgerEntityAddBindingModel ledgerEntityAddBindingModel,
                                         BindingResult bindingResult, ModelAndView mav, RedirectAttributes redirectAttributes,
                                         @AuthenticationPrincipal Principal principal) {
        ledgerEntityAddBindingModel.setUser(principal.getName());
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("ledgerEntityAddBindingModel", ledgerEntityAddBindingModel);
            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "ledgerEntityAddBindingModel",
                    bindingResult);
            mav.setViewName("redirect:/tables");
        }else {
            boolean isInvoice = false;
            LedgerEntityAddServiceModel ledgerEntityAddServiceModel = this.ledgerEntityService.postLedgerEntity(this.modelMapper.map(ledgerEntityAddBindingModel, LedgerEntityAddServiceModel.class), isInvoice);
            mav.setViewName("redirect:/tables");
        }
        return mav;
    }

    @GetMapping("/list-ledger-entity")
    public ModelAndView listLedgerEntity (@AuthenticationPrincipal Principal principal){
        ModelAndView mav= new ModelAndView("list-ledger-entity");
        List<LedgerEntityViewModel> generalLedgerEntityViewModels=
                this.ledgerEntityService.findAll().stream()
                        .map(ledgerEntityyServiceModel -> modelMapper
                                .map(ledgerEntityyServiceModel,LedgerEntityViewModel.class))
                .collect(Collectors.toList());
        mav.addObject("ledger",generalLedgerEntityViewModels);
        return mav;
   }
}
