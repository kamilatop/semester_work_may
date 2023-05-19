package ru.itis.dmitrieva.helper;

import lombok.RequiredArgsConstructor;
import ru.itis.dmitrieva.models.Account;
import ru.itis.dmitrieva.repositories.AccountRepository;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

//реализация польовательского валидатора, который проверяет уникальность мейла

@RequiredArgsConstructor
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    private final AccountRepository accountRepository;
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {//метод принимает значение которое бует проверяться и контекст валидации
        Optional<Account> account = accountRepository.findByEmail(value);//есть ли в репозитории  аккаунт с указанным элек адресом
        if (account.isPresent()) {
            return false;
        }
        return true;
    }
}
