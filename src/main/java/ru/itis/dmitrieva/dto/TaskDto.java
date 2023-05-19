package ru.itis.dmitrieva.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.dmitrieva.models.Tasks;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDto {
    private Long id;
    private String title;
    private boolean completed;

    public static TaskDto from(Tasks task) {
        return TaskDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .completed(task.isCompleted())
                .build();
    }

    public static List<TaskDto> from(List<Tasks> tasks) {
        return tasks.stream().map(TaskDto::from).collect(Collectors.toList());
    }
}
