package gamma.web;

import gamma.model.binding.LedgerEntityAddBindingModel;
import gamma.model.binding.LedgerEntityApprovedBindingModel;
import gamma.model.binding.UserAddBindingModel;
import gamma.model.view.LedgerEntityByIsPaidViewModel;
import gamma.service.LedgerEntityService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping
public class HomeController {
    private final LedgerEntityService ledgerEntityService;
    private final ModelMapper modelMapper;

    public HomeController(LedgerEntityService ledgerEntityService, ModelMapper modelMapper) {
        this.ledgerEntityService = ledgerEntityService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public ModelAndView index(String principal, ModelAndView mav) {
       if (principal == null) {
            mav.setViewName("index");
            return mav;
        }
        List<LedgerEntityByIsPaidViewModel> ledgerEntityByIsPaidViewModels =
                this.ledgerEntityService.findAllByPayed().stream()
                        .map(ledgerEntityyServiceModel -> modelMapper
                                .map(ledgerEntityyServiceModel, LedgerEntityByIsPaidViewModel.class))
                        .collect(Collectors.toList());
        LedgerEntityAddBindingModel ledgerEntityAddBindingModel = new LedgerEntityAddBindingModel();
        mav.addObject("ledgerEntityAddBindingModel", ledgerEntityAddBindingModel);
        mav.addObject("ledgers", ledgerEntityByIsPaidViewModels);
        mav.setViewName("home");
        return mav;
    }

    @GetMapping("/homes")
    public ModelAndView homeAbsolute(@AuthenticationPrincipal Principal principal, ModelAndView model) {
        String name = principal.getName();
        model.addObject("user", principal);
        return index(name, model);
    }

    @PostMapping("/homes")
    public String homePost() {
        return "redirect:/homes";
    }

    @GetMapping("/approve")
    public ModelAndView approve(@Valid @ModelAttribute("LedgerEntityApprovedBindingModel") LedgerEntityApprovedBindingModel LedgerEntityApprovedBindingModel,
                                BindingResult bindingResult, ModelAndView mav) {
        mav.addObject("LedgerEntityApprovedBindingModel", LedgerEntityApprovedBindingModel);
        List<LedgerEntityByIsPaidViewModel> ledgerEntityByIsPaidViewModels =
                this.ledgerEntityService.findAllByPayed().stream()
                        .map(ledgerEntityyServiceModel -> modelMapper
                                .map(ledgerEntityyServiceModel, LedgerEntityByIsPaidViewModel.class))
                        .collect(Collectors.toList());
        mav.addObject("ledgers", ledgerEntityByIsPaidViewModels);
        mav.setViewName("approve");
        return mav;
    }

    @PostMapping("/approve")
    public ModelAndView approve(@Valid @ModelAttribute("LedgerEntityApprovedBindingModel") LedgerEntityApprovedBindingModel LedgerEntityApprovedBindingModel,
                          BindingResult bindingResult, RedirectAttributes redirectAttributes, ModelAndView mav) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("LedgerEntityApprovedBindingModel", LedgerEntityApprovedBindingModel);
            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "LedgerEntityApprovedBindingModel",
                    bindingResult);
            mav.setViewName("redirect:/approve");
        } else {
            if (!ledgerEntityService.existsUser(LedgerEntityApprovedBindingModel.getIds())) {
                bindingResult.rejectValue("ids",
                        "errors.ids",
                        "There isn`t ledger entity with this id.");
                mav.setViewName("401");
            } else {
                this.ledgerEntityService.isApproved(LedgerEntityApprovedBindingModel.getIds());
                mav.setViewName("redirect:/approve");
            }
        }
        return mav;
    }

    @PostMapping("/approvego")
    public String approvePost() {
        return "redirect:/approve";
    }
}
