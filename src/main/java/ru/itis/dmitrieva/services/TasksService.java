package ru.itis.dmitrieva.services;

import ru.itis.dmitrieva.dto.TaskDto;
import java.util.List;

public interface TasksService {
    List<TaskDto> getAllTasks();
    TaskDto addTask(TaskDto taskDto);
    void deleteTask(Long id);
    TaskDto updateTask(Long id, TaskDto taskDto);
}
