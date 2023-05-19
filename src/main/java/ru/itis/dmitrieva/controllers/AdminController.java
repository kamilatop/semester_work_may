package ru.itis.dmitrieva.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.dmitrieva.services.AccountService;

//контроллер в-п, обрабатывает запросы с управлением в админке

@RequiredArgsConstructor
@Controller //контроллер, обрабатывающий веб-запросы
@RequestMapping("/accounts") //этим контроллером
public class AdminController {

    private final AccountService accountsService;

    @PreAuthorize("hasAuthority('ADMIN')") //определяет правило авторизации (роль админ)
    @GetMapping//гет-запросы
    public String getAccountsPage(Model model) {
        model.addAttribute("accounts", accountsService.getAllAccounts());//добавляет атрибут accounts c помощью метода addAttribute

        return "accounts";
    } //обрабатыает все гет-запросы с accounts

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{account-id}/delete")//пост-запросы
    public String deleteAccount(@PathVariable("account-id") Long accountId) {
        accountsService.deleteAccount(accountId);
        return "redirect:/accounts"; //после удаления возвращает
    }
}
