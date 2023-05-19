package ru.itis.dmitrieva.util;

import java.util.Map;

public interface EmailUtil {
    void sendMail(String to, String subject, String templateName, Map<String, String> data);
}
//для отправки электронной почты получателю с указанной темой и содержимым, основанным на заданном шаблоне