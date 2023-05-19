package ru.itis.dmitrieva.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.dmitrieva.dto.TaskDto;
import ru.itis.dmitrieva.services.TasksService;
import java.util.List;

//точки входа rest api для выполнения задач


@RequiredArgsConstructor //lmbk
@RestController //обрабатывает http запросы rest api
public class TasksRestController { //контроллер rest api для crud сущности

    private final TasksService tasksService;

    @GetMapping("tasks/getAllTasks") //гет запрос
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        return ResponseEntity.ok(tasksService.getAllTasks());
    } //предоставляющий ответ на хттп запрос, ответ: состояние 200 ОК и список задач

    @PostMapping(value ="tasks/addTask") //пост запрос
    public ResponseEntity<TaskDto> addTasks(@RequestBody TaskDto taskDto) { //принимает TaskDto
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tasksService.addTask(taskDto)); //код состояния 201 создан, тело ответа - добавленную задачу
    }

    @PutMapping("tasks/update/{task-id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable("task-id") Long id,
                                              @RequestBody TaskDto task) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(tasksService.updateTask(id, task));
    } //для обновления задачи, 202 принят и тело обновленная задача

    @DeleteMapping("tasks/deleteTask/{task-id}")
    public void deleteTask(@PathVariable("task-id") Long id) {
        tasksService.deleteTask(id);
    } //для удаления задачи, ResponseEntity не возвращается, просто удаление
}
