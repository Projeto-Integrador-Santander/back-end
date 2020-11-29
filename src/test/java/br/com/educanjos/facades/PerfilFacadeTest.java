package br.com.educanjos.facades;

import br.com.educanjos.infra.handler.model.ExceptionEducanjosApi;
import br.com.educanjos.models.entities.Perfil;
import br.com.educanjos.repositories.PerfilRepository;
import br.com.educanjos.templates.PerfilTemplate;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
public class PerfilFacadeTest {

    @InjectMocks
    private PerfilFacade facade;

    @Mock
    private PerfilRepository repository;

    private static Perfil perfilTemplate;

    @Before
    public void init() {
        initMocks(this);
        FixtureFactoryLoader.loadTemplates("br.com.educanjos.templates");
        perfilTemplate = Fixture.from(Perfil.class).gimme(PerfilTemplate.PERFIL_VALIDO);
    }

    @Test
    public void deveSalvarPerfil(){
        when(repository.findByCpf(anyString())).thenReturn(null);
        when(repository.save(any(Perfil.class))).thenReturn(perfilTemplate);
        Perfil saida = facade.newPerfil(perfilTemplate);
        assertNotNull(saida);
    }


    @Test
    public void deveSalvarPerfilExceptionCPFExistente(){
        when(repository.findByCpf(anyString())).thenReturn(perfilTemplate);
        when(repository.save(any(Perfil.class))).thenReturn(perfilTemplate);
        try {
            facade.newPerfil(perfilTemplate);
        }catch (ExceptionEducanjosApi ex){
            assertEquals("VALIDACAO-0", ex.getCodErro());
            assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
            assertNotNull(ex.getMessage());
        }
    }

    @Test
    public void deveBuscarPerfil(){
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(perfilTemplate));
        Perfil saida = facade.getPerfilById(1L);
        assertNotNull(saida);
    }

    @Test
    public void deveBuscarPerfilException(){
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        try {
            facade.getPerfilById(1L);
        }catch (ExceptionEducanjosApi ex){
            assertEquals("VALIDACAO-1", ex.getCodErro());
            assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
            assertNotNull(ex.getMessage());
        }
    }

    @Test
    public void deveBuscarTodosPerfil(){
        when(repository.findAll()).thenReturn(Collections.singletonList(perfilTemplate));
        List<Perfil> saida = facade.getAllPerfil();
        assertNotNull(saida);
        assertFalse(saida.isEmpty());
    }

    @Test
    public void deveBuscarTodosPerfilException(){
        when(repository.findAll()).thenReturn(Collections.EMPTY_LIST);
        try {
            facade.getAllPerfil();
        }catch (ExceptionEducanjosApi ex){
            assertEquals("VALIDACAO-2", ex.getCodErro());
            assertEquals(HttpStatus.NO_CONTENT, ex.getStatus());
            assertNotNull(ex.getMessage());
        }
    }

    @Test
    public void deveDeletarPerfil(){
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(perfilTemplate));
        doNothing().when(repository).deleteById(anyLong());
        try {
            facade.deletePerfil(1L);
        }catch (Exception ex){
            fail();
        }
    }

}
