package ru.itis.dmitrieva.helper;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
//проверка сложности пароля
public class PasswordValidator implements ConstraintValidator<Validator, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.matches("(?=.*\\d+.*)(?=.*[A-Z]+.*)\\w{4,12}");
    }
}

