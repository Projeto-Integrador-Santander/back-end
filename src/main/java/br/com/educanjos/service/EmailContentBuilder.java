package br.com.educanjos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailContentBuilder {

    @Autowired
    private TemplateEngine templateEngine;

    public String build(String text) {
        Context context = new Context();
        context.setVariable("corpoEmail", text);
        return templateEngine.process(new ClassPathResource("template-email").getPath(), context);
    }
}