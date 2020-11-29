package br.com.educanjos.templates;

import br.com.educanjos.models.entities.RequisicaoSenha;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.time.LocalDateTime;

public class RequisicaoSenhaTemplate implements TemplateLoader {

    public static final String REQUISICAO_SENHA_VALIDO = "REQUISICAO_SENHA_VALIDO";

    @Override
    public void load() {
        Fixture.of(RequisicaoSenha.class).addTemplate(REQUISICAO_SENHA_VALIDO, new Rule() {{
            add("token", "tokengeradorandom");
            add("email", "email@teste.com");
            add("lastUpdate", LocalDateTime.now());
            add("dataExpiracao", LocalDateTime.now().plusMinutes(30));
        }});
    }

}
