package br.com.educanjos.templates;

import br.com.educanjos.models.entities.Perfil;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class PerfilTemplate implements TemplateLoader {

    public static final String PERFIL_VALIDO = "PERFIL_VALIDO";

    @Override
    public void load() {
        Fixture.of(Perfil.class).addTemplate(PERFIL_VALIDO, new Rule() {{
            add("id", 1L);
            add("nome", "nome");
            add("sobrenome", "sobrenome");
            add("cpf", "621.310.040-79");
            add("telefone", "11999999999");
            add("urlFoto", "https://teste.com");
            add("sobre", "");
        }});
    }
}
