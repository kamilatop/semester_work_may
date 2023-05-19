package ru.itis.dmitrieva.dto;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.dmitrieva.models.Account;
import ru.itis.dmitrieva.models.Class;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassDto {

    private Long id;

    private String name;

    private String time;

    private List<String> accounts;

    public static ClassDto from(Class classes) {
        ClassDto classDto = ClassDto.builder()
                .id(classes.getId())
                .name(classes.getName())
                .time(classes.getTime())
                .build();

        if (classes.getAccounts() != null) {
            classDto.setAccounts(classes.getAccounts().stream().map(Account::getLastName).collect(Collectors.toList()));
        }
        return classDto;
    }

    public static List<ClassDto> from(List<Class> classes) {
        return classes.stream().map(ClassDto::from).collect(Collectors.toList());
    }
}
