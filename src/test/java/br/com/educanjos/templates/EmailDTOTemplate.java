package br.com.educanjos.templates;

import br.com.educanjos.models.dto.EmailDTO;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class EmailDTOTemplate implements TemplateLoader {

    public static final String EMAIL_VALIDO = "EMAIL_VALIDO";

    @Override
    public void load() {
        Fixture.of(EmailDTO.class).addTemplate(EMAIL_VALIDO, new Rule() {{
            add("token", "tokengeradorandom");
            add("email", "email@teste.com");
            add("senha", "senha");
        }});
    }
}
