package ru.itis.dmitrieva.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signIn")
public class SignInController {

    @GetMapping
    public String getSignInPage(Authentication authentication) {
        if (authentication != null) {
            return "redirect:/";
        }
        return "sign_in";
    }
}

