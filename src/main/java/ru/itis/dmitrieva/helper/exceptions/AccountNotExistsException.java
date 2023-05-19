package ru.itis.dmitrieva.helper.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


//для обработки ситуаций когда запрашиваемый аккаунт не существует
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AccountNotExistsException extends RuntimeException {
    public AccountNotExistsException() {
        super();
    }
}
