package br.com.educanjos.infra.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailContentBuilder {

    @Autowired
    private TemplateEngine templateEngine;

    public String build(String text, String saudacao) {
        Context context = new Context();
        context.setVariable("corpoEmail", text);
        context.setVariable("saudacao", saudacao);
        return templateEngine.process(new ClassPathResource("template-email").getPath(), context);
    }
}