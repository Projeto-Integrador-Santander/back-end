package br.com.educanjos.templates;

import br.com.educanjos.models.entities.Login;
import br.com.educanjos.models.entities.Materia;
import br.com.educanjos.models.entities.Perfil;
import br.com.educanjos.models.entities.Pessoa;
import br.com.educanjos.models.enums.TipoCadastroPessoa;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class PessoaTemplate implements TemplateLoader {

    public static final String PESSOA_VALIDO = "PESSOA_VALIDO";

    @Override
    public void load() {
        Fixture.of(Pessoa.class).addTemplate(PESSOA_VALIDO, new Rule() {{
            add("id", 1L);
            add("tipoCadastro", TipoCadastroPessoa.ALUNO);
            add("login", one(Login.class, LoginTemplate.LOGIN_VALIDO));
            add("materias", has(25).of(Materia.class, MateriaTemplate.MATERIA_VALIDO));
            add("perfil", one(Perfil.class, PerfilTemplate.PERFIL_VALIDO));
        }});
    }
}
