package ru.itis.dmitrieva.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.dmitrieva.dto.AccountDto;
import ru.itis.dmitrieva.dto.PostDto;
import ru.itis.dmitrieva.models.Account;
import ru.itis.dmitrieva.security.details.AccountUserDetails;
import ru.itis.dmitrieva.services.AccountService;
import ru.itis.dmitrieva.services.FileService;
import ru.itis.dmitrieva.services.PostService;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final PostService postService;

    private final AccountService accountService;

    private final FileService fileService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String getProfilePage(@AuthenticationPrincipal AccountUserDetails userDetails, Model model) {
        Optional<Account> accountByEmail = accountService.getAccountByEmail(userDetails.getUsername());
        if (accountByEmail.isPresent()) {
            Account account = accountByEmail.get();
            model.addAttribute("account", account);
        }
        return "profile";
    } //сначала получает учетную запись пользователя , если она существует, она добавляется в модель, затем возвращает имя представления

    @PreAuthorize("isAuthenticated()")
    @PostMapping("profile/{account-id}/post")
    public String addPost(@PathVariable("account-id") Long accountId, @Valid PostDto post, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/profile";
        }
        postService.addPost(post, accountId);
        return "redirect:/profile";
    }

    @GetMapping("/profile/update")
    public String getEditPage(Model model) {
        model.addAttribute("accountDto", new AccountDto());
        return "changes";
    }//для редактирования профиля

    @PostMapping("/profile/update")
    public String update(@Valid AccountDto accountDto, BindingResult result, @AuthenticationPrincipal AccountUserDetails userDetails, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("accountDto", accountDto);
            return "changes";
        }
        String email = userDetails.getUsername();
        accountService.update(accountDto, email);
        return "redirect:/profile";
    }

    @GetMapping("/profile/files/{filename}")
    public void getFile(@PathVariable(name = "filename") String storageFileName, HttpServletResponse response) {
        fileService.getFileByStorageName(storageFileName, response); //чтобы получить файл по его имени и отправить в ответ
    }

    @PostMapping("profile/files/{account-id}/upload")
    public String uploadAvatar(@PathVariable(name = "account-id") Long accountId, @RequestParam("file") MultipartFile multipartFile) {
        if (multipartFile.getOriginalFilename().isEmpty()) {
            return "redirect:/profile";
        }
        fileService.uploadAvatar(multipartFile, accountId);
        return "redirect:/profile";
    }
}

