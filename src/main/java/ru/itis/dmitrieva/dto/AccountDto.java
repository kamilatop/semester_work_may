package ru.itis.dmitrieva.dto;

import ru.itis.dmitrieva.helper.Validator;
import ru.itis.dmitrieva.models.Account;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

//для передачи информации о пользователе между различными компонентами системы

@Data //генерирует автоматически методы ... для всех полей класса
@AllArgsConstructor //создает конструктор, который принимает все аргументы для инициализации полей класса
@NoArgsConstructor //создает конструктор без аргументов
@Builder//похволяет создавать объекты класса с помощью цепочки вызовов методов
public class AccountDto {

    private Long id;

    @NotBlank
    @Size(min = 2, max = 20)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 50)
    private String lastName;

    @Validator //кастомная валидация
    private String password;

    private String email;

    private String faculty_name;

    private FileDto avatar;

    public static AccountDto from(Account account) { //преобразует объект класса аккаунт в аккунтдто
        return AccountDto.builder()
                .id(account.getId())
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .password(account.getPassword())
                .email(account.getEmail())
                .build();
    }

    public static List<AccountDto> from(List<Account> accounts) { //то же самое но со списками
        return accounts.stream().map(AccountDto::from).collect(Collectors.toList());
    }
}
