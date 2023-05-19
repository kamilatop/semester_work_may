package ru.itis.dmitrieva.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.dmitrieva.dto.PostDto;
import ru.itis.dmitrieva.services.PostService;
import java.util.List;

//отображение всех постов, поиска постов по заголовку и контроль доступа для аунтифицированных пользователей

@RequiredArgsConstructor
@Controller
@RequestMapping("/posts")
public class PostsController {

    private final PostService postService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public String getAccountsPage(Model model) {
        model.addAttribute("posts", postService.getAllPosts()); //добавляет атрибут постс в модель с помощью метода addAttribute, значение  этого атрибута получается с помощью вызова метода getAllPosts
        return "posts";
    }

    @GetMapping("/search")
    public String getPostsSearchPage() {
        return "searchPost";
    } //для отображения страницы поиска постов

    @GetMapping(value = "/search/{title}", produces = MediaType.APPLICATION_JSON_VALUE)//результат метода должен быть преобразован в тело ответа, в формат Json
    @ResponseBody
    public List<PostDto> searchPostByTitle(@PathVariable("title") String title) {
        return postService.searchPostByTitle(title);
    }//метод вызывает postService, который выполняет поиск постов по указанному заголовку  и возвращает спписок найденных постов в формате Лист
}
