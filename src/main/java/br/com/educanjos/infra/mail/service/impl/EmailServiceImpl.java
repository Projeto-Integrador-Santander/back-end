package br.com.educanjos.infra.mail.service.impl;

import br.com.educanjos.infra.mail.model.EnvioEmail;
import br.com.educanjos.infra.mail.service.EmailContentBuilder;
import br.com.educanjos.infra.mail.service.EmailService;
import br.com.educanjos.infra.handler.model.ExceptionEducanjosApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${projeto.integrador.email}")
    private String email;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private EmailContentBuilder mailContentBuilder;

    @Override
    public void enviarEmail(EnvioEmail envioEmail) {
        MimeMessagePreparator preparator = mimeMessage -> {

            MimeMessageHelper message = new MimeMessageHelper (mimeMessage);
            message.setTo(envioEmail.getEmail());
            message.setFrom(email);
            message.setSubject(envioEmail.getAssunto());
            String content = mailContentBuilder.build(envioEmail.getCorpoEmail(), envioEmail.getSaudacao());
            message.setText(content, true);
        };
        try {
            mailSender.send(preparator);
            System.out.println("mail send");
        }catch (MailException e) {
            throw new ExceptionEducanjosApi(HttpStatus.INTERNAL_SERVER_ERROR, "EMAIL-0");
        }catch (Exception e){
            throw new ExceptionEducanjosApi(HttpStatus.INTERNAL_SERVER_ERROR, "EMAIL-1", e.getMessage());
        }
    }
}
