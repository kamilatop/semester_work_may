package ru.itis.dmitrieva.helper;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
//для обозначения поля пароля
@Constraint(validatedBy = PasswordValidator.class)//должна быть проверена сложность с помощью PasswordValidator
@Target({ElementType.METHOD, ElementType.FIELD})//применима к методам и полям
@Retention(RetentionPolicy.RUNTIME) //должна быть доступна во время выполнения программы
public @interface Validator {

    String message() default "invalid password"; //сообщение которое будет возвращено в случае невыполнения валидации

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default  {};

}
