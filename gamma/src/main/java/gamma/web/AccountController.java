package gamma.web;

import gamma.model.binding.AccountsAddBindingModel;
import gamma.model.binding.VendorsAddBindingModel;
import gamma.model.service.AccountServiceModel;
import gamma.model.service.UserServiceModel;
import gamma.model.service.VendorServiceModel;
import gamma.model.view.AccountViewModel;
import gamma.model.view.ContragentViewModel;
import gamma.service.AccountService;
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
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;
    private final ModelMapper modelMapper;

    public AccountController(AccountService accountService, ModelMapper modelMapper) {
        this.accountService = accountService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/list")
    public ModelAndView accounts(@AuthenticationPrincipal Principal principal){
        ModelAndView mav= new ModelAndView("accounts");
        List<AccountViewModel> accountViewModels = this.accountService.findAll().stream().
                map(a -> modelMapper.map(a, AccountViewModel.class) )
                .collect(Collectors.toList());
        System.out.println();
        mav.addObject("listOfAccounts", accountViewModels);
        mav.addObject("user", principal);
        return mav;
    }

    @GetMapping("/new")
    public ModelAndView newAccounts(@Valid @ModelAttribute("accountsAddBindingModel")AccountsAddBindingModel accountsAddBindingModel){
        ModelAndView mav= new ModelAndView("new-account");
        mav.addObject("accountsAddBindingModel",accountsAddBindingModel);
        return mav;
    }

    @PostMapping("/new")
    public ModelAndView newAccountsPost(@Valid @ModelAttribute("accountsAddBindingModel")AccountsAddBindingModel accountsAddBindingModel,
                                        BindingResult bindingResult, ModelAndView mav, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("accountsAddBindingModel", accountsAddBindingModel);
            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "accountsAddBindingModel",
                    bindingResult);
            mav.setViewName("new-account");
        }else {
            if (accountService.existsAccount(accountsAddBindingModel.getAccountNumber())) {
                bindingResult.rejectValue("accountNumber",
                        "errors.accountNumber",
                        "An account with this accountNumber already exists.");
                mav.setViewName("new-account");
                redirectAttributes.addFlashAttribute("accountsAddBindingModel", accountsAddBindingModel);
                redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "accountsAddBindingModel",
                        bindingResult);
            } else {
                AccountServiceModel asm = new AccountServiceModel();
                asm.setBalance(BigDecimal.valueOf(0));
                asm.setAccountNumber(accountsAddBindingModel.getAccountNumber());
                asm.setActivePassive(accountsAddBindingModel.getActivePassive());
                asm.setBalanceIncome(accountsAddBindingModel.getBalanceIncome());
                asm.setCredit(BigDecimal.valueOf(0));
                asm.setDebit(BigDecimal.valueOf(0));
                asm.setName(accountsAddBindingModel.getName());
                this.accountService.addAccount(asm);
                mav.setViewName("redirect:/accounts/list");
            }
        }
          return mav;
    }
}
