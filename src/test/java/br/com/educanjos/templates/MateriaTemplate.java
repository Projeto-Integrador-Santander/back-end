package br.com.educanjos.templates;

import br.com.educanjos.models.entities.Materia;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class MateriaTemplate implements TemplateLoader {

    public static final String MATERIA_VALIDO = "MATERIA_VALIDO";

    @Override
    public void load() {
        Fixture.of(Materia.class).addTemplate(MATERIA_VALIDO, new Rule() {{
            add("id", random(1L, 0L));
            add("materia", "fisica");
            add("descricao", "");
        }});
    }
}
