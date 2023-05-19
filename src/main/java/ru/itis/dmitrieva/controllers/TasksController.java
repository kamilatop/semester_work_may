package ru.itis.dmitrieva.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//при обращении к таскс будет отображена страница с задачами

@RequiredArgsConstructor //создает конструктор класса автоматически внедряющий зависимости если с final
@Controller
@RequestMapping("/tasks")
public class TasksController {

    @GetMapping
    public String getTasksPage() {

        return "tasks";
    }
}
