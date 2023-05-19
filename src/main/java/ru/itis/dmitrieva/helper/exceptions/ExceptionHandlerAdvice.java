package ru.itis.dmitrieva.helper.exceptions;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

//ля обработки исключений в контроллерах приложения

@ControllerAdvice //для всех контроллеров
@Log4j2
public class ExceptionHandlerAdvice {
   @ExceptionHandler(AccountNotExistsException.class)//данный метод будет обрабатывать исключения типа ...
    public ModelAndView handleError(HttpServletRequest request, AccountNotExistsException exception) { //внутри метода происходит логирование ошибки с помощью библиотеки
    log.error("Request: " + request.getRequestURL() + " raised " + exception);
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("exception", exception); //добавляем атрибут
    modelAndView.addObject("url", request.getRequestURL());//добавляем атрибут
    modelAndView.setViewName("account is not found");
    return modelAndView;
   }
}
