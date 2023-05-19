package ru.itis.dmitrieva.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.dmitrieva.models.Account;
import ru.itis.dmitrieva.security.details.AccountUserDetails;
import ru.itis.dmitrieva.services.ClassService;

//контроллер, обрабатывающий запросы с управлением групп в системе. позволяет получить страницу со списком классов

@RequiredArgsConstructor
@Controller
@RequestMapping("/classes")
public class ClassesController {

    private final ClassService classService;

    @PreAuthorize("isAuthenticated()") //требует, чтобы пользователь был залогинен
    @GetMapping
    public String getClassesPage(Model model, @AuthenticationPrincipal AccountUserDetails userDetails) {
        Account account = userDetails.getAccount(); //получение учетной записи из uD с помощью метода
        model.addAttribute("classes", classService.findClassesByAccountsId(account.getId()));
        return "classes"; //имя представления для отображения страниц с классами
    } //обрабатывает гет-запросы
}
