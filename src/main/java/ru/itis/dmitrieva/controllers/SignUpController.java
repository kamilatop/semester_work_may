package ru.itis.dmitrieva.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.dmitrieva.dto.SignUpForm;
import ru.itis.dmitrieva.services.AccountService;
import javax.validation.Valid;
//регистрация
@Controller
@RequiredArgsConstructor
public class SignUpController {

    private final AccountService accountService;

    @GetMapping("/signUp")
    public String getSignUpPage(Authentication authentication, Model model) {
        if (authentication != null) {
            return "redirect:/";
        }
        model.addAttribute("signUpForm", new SignUpForm());
        return "sign_up";
    }

    @PostMapping("/signUp")
    public String signUp(@Valid SignUpForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("signUpForm", form);
            return "sign_up";
        }
        accountService.signUp(form);
        return "redirect:/signIn";
    }

    @GetMapping(value="/confirm")
    public String accountVerified(@RequestParam(value="code") String confirmCode){
        accountService.updateState(confirmCode);//обновляет состояние аккаунта
        return "verified";
    }
}


