package br.com.educanjos.templates;

import br.com.educanjos.models.entities.PessoaAgenda;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.time.LocalTime;

public class PessoaAgendaTemplate implements TemplateLoader {
    public static final String PESSOA_AGENDA_VALIDO = "PESSOA_AGENDA_VALIDO";

    @Override
    public void load() {
        Fixture.of(PessoaAgenda.class).addTemplate(PESSOA_AGENDA_VALIDO, new Rule() {{
            add("id", 1L);
            add("pessoaId", 1L);
            add("materiaId", 1L);
            add("diaSemana", 1L);
            add("voluntario", true);
            add("inicio", LocalTime.now());
            add("fim", LocalTime.now());
        }});
    }
}
