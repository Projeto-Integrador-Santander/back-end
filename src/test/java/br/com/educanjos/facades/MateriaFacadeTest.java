package br.com.educanjos.facades;

import br.com.educanjos.infra.handler.model.ExceptionEducanjosApi;
import br.com.educanjos.models.entities.Materia;
import br.com.educanjos.repositories.MateriaRepository;
import br.com.educanjos.templates.MateriaTemplate;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
public class MateriaFacadeTest {

    @InjectMocks
    private MateriaFacade facade;

    @Mock
    private MateriaRepository repository;

    private static Materia materiaTemplate;

    @Before
    public void init() {
        initMocks(this);
        FixtureFactoryLoader.loadTemplates("br.com.educanjos.templates");
        materiaTemplate = Fixture.from(Materia.class).gimme(MateriaTemplate.MATERIA_VALIDO);
    }

    @Test
    public void deveSalvarMateria(){
        when(repository.save(any(Materia.class))).thenReturn(materiaTemplate);
        Materia saida = facade.newMateria(materiaTemplate);
        assertNotNull(saida);
    }

    @Test
    public void deveBuscarMateria(){
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(materiaTemplate));
        Materia saida = facade.getMateriaById(1L);
        assertNotNull(saida);
    }

    @Test
    public void deveBuscarMateriaException(){
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        try {
            facade.getMateriaById(1L);
        }catch (ExceptionEducanjosApi ex){
            assertEquals("VALIDACAO-1", ex.getCodErro());
            assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
            assertNotNull(ex.getMessage());
        }
    }

    @Test
    public void deveBuscarTodasMateria(){
        when(repository.findAll()).thenReturn(Collections.singletonList(materiaTemplate));
        List<Materia> saida = facade.getAllMateria();
        assertNotNull(saida);
        assertFalse(saida.isEmpty());
    }

    @Test
    public void deveBuscarTodasMateriaException(){
        when(repository.findAll()).thenReturn(Collections.EMPTY_LIST);
        try {
            facade.getAllMateria();
        }catch (ExceptionEducanjosApi ex){
            assertEquals("VALIDACAO-2", ex.getCodErro());
            assertEquals(HttpStatus.NO_CONTENT, ex.getStatus());
            assertNotNull(ex.getMessage());
        }
    }

    @Test
    public void deveDeletarMateria(){
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(materiaTemplate));
        doNothing().when(repository).deleteById(anyLong());
        try {
            facade.deleteMateria(1L);
        }catch (Exception ex){
            fail();
        }
    }

}
