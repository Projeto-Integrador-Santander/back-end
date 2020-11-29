package br.com.educanjos.templates;

import br.com.educanjos.models.entities.Login;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class LoginTemplate implements TemplateLoader {

    public static final String LOGIN_VALIDO = "LOGIN_VALIDO";
    public static final String LOGIN_VALIDO_2 = "LOGIN_VALIDO_2";

    @Override
    public void load() {
        Fixture.of(Login.class).addTemplate(LOGIN_VALIDO, new Rule() {{
            add("id", 1L);
            add("email", "email@teste.com");
            add("senha", "senha");
        }})
        .addTemplate(LOGIN_VALIDO_2, new Rule() {{
            add("id", 1L);
            add("email", "email2@teste.com");
            add("senha", "senha");
        }});
    }
}
