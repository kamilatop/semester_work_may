package ru.itis.dmitrieva.helper;

import javax.validation.Payload;
import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


//для обозначения поля электронной почты
@Constraint(validatedBy = EmailValidator.class)//должна быть проверена с помощью класса EmailValidator
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEmail {

    String message() default "invalid email";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default  {};
}
