package ru.itis.dmitrieva.util;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import java.io.IOException;
import java.util.Map;

//реализация интерфейса EmailUtil

@RequiredArgsConstructor
@Component
public class EmailUtilImpl implements EmailUtil {

    @Autowired
    private final JavaMailSender mailSender;

    @Autowired
    private final Configuration configuration;

    @Value("${spring.mail.username}")
    private String from;
//шаблон письма обрабатывается с помощью FreeMarkerTemplateUtils,
// используя configuration и переданные данные data. Полученный текст письма сохраняется в mailText
    @Override
    public void sendMail(String to, String subject, String templateName, Map<String, String> data) {
        MimeMessagePreparator mimeMessagePreparator;
        try {
            try {
                final String mailText = FreeMarkerTemplateUtils.processTemplateIntoString(configuration.getTemplate(templateName, "UTF-8"), data);
                System.out.println(mailText);
                mimeMessagePreparator = mimeMessage -> {
                    MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
                    messageHelper.setSubject(subject);
                    messageHelper.setText(mailText, true);
                    messageHelper.setTo(to);
                    messageHelper.setFrom(from);
                };
                mailSender.send(mimeMessagePreparator);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

}
