package br.com.educanjos.facades;

import br.com.educanjos.infra.handler.model.ExceptionEducanjosApi;
import br.com.educanjos.models.entities.PessoaAgenda;
import br.com.educanjos.repositories.PessoaAgendaRepository;
import br.com.educanjos.templates.PessoaAgendaTemplate;
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
public class PessoaAgendaFacadeTest {

    @InjectMocks
    private AgendaFacade  facade;

    @Mock
    private PessoaAgendaRepository repository;

    private static PessoaAgenda pessoaAgendaTemplate;

    @Before
    public void init() {
        initMocks(this);
        FixtureFactoryLoader.loadTemplates("br.com.educanjos.templates");
        pessoaAgendaTemplate = Fixture.from(PessoaAgenda.class).gimme(PessoaAgendaTemplate.PESSOA_AGENDA_VALIDO);
    }

    @Test
    public void deveSalvarPessoaAgenda(){
        when(repository.save(any(PessoaAgenda.class))).thenReturn(pessoaAgendaTemplate);
        PessoaAgenda saida = facade.newAgenda(pessoaAgendaTemplate);
        assertNotNull(saida);
    }

    @Test
    public void deveAtualizarPessoaAgenda(){
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(pessoaAgendaTemplate));
        when(repository.save(any(PessoaAgenda.class))).thenReturn(pessoaAgendaTemplate);
        try {
            facade.atualizaAgenda(1L, pessoaAgendaTemplate);
        }catch (Exception ex){
            fail();
        }
    }

    @Test
    public void deveBuscarPessoaAgenda(){
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(pessoaAgendaTemplate));
        PessoaAgenda saida = facade.getAgendaById(1L);
        assertNotNull(saida);
    }

    @Test
    public void deveBuscarPessoaAgendaException(){
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        try {
            facade.getAgendaById(1L);
        }catch (ExceptionEducanjosApi ex){
            assertEquals("VALIDACAO-1", ex.getCodErro());
            assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
            assertNotNull(ex.getMessage());
        }
    }

    @Test
    public void deveBuscarTodasPessoaAgenda(){
        when(repository.findAll()).thenReturn(Collections.singletonList(pessoaAgendaTemplate));
        List<PessoaAgenda> saida = facade.getAllAgenda();
        assertNotNull(saida);
        assertFalse(saida.isEmpty());
    }

    @Test
    public void deveBuscarTodasPessoaAgendaException(){
        when(repository.findAll()).thenReturn(Collections.EMPTY_LIST);
        try {
            facade.getAllAgenda();
        }catch (ExceptionEducanjosApi ex){
            assertEquals("VALIDACAO-2", ex.getCodErro());
            assertEquals(HttpStatus.NO_CONTENT, ex.getStatus());
            assertNotNull(ex.getMessage());
        }
    }

    @Test
    public void deveDeletarPessoaAgenda(){
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(pessoaAgendaTemplate));
        doNothing().when(repository).deleteById(anyLong());
        try {
            facade.deleteById(1L);
        }catch (Exception ex){
            fail();
        }
    }

    @Test
    public void deveBuscarPessoaIDDiaSemana(){
        when(repository.getByPessoaIdAndDiaSemana(anyLong(), anyLong())).thenReturn(Collections.singletonList(pessoaAgendaTemplate));
        List<PessoaAgenda> saida = facade.getByPessoaIdAndDiaSemana(1L, 1L);
        assertNotNull(saida);
        assertFalse(saida.isEmpty());
    }

    @Test
    public void deveBuscarPessoaIDDiaSemanaAgendaException(){
        when(repository.findAll()).thenReturn(Collections.EMPTY_LIST);
        try {
            facade.getByPessoaIdAndDiaSemana(1L, 1L);
        }catch (ExceptionEducanjosApi ex){
            assertEquals("VALIDACAO-2", ex.getCodErro());
            assertEquals(HttpStatus.NO_CONTENT, ex.getStatus());
            assertNotNull(ex.getMessage());
        }
    }

    @Test
    public void deveBuscarAgendaMateriaId(){
        when(repository.findAllByMateriaIdIn(any())).thenReturn(Collections.singletonList(pessoaAgendaTemplate));
        List<PessoaAgenda> saida = facade.getByMateriasIdAndDiaSemana(Collections.singletonList(1L), 0L);
        assertNotNull(saida);
        assertFalse(saida.isEmpty());
    }

    @Test
    public void deveBuscarAgendaMateriaIdDiaSemana(){
        when(repository.findAllByMateriaIdInAndDiaSemana(any(), anyLong())).thenReturn(Collections.singletonList(pessoaAgendaTemplate));
        List<PessoaAgenda> saida = facade.getByMateriasIdAndDiaSemana(Collections.singletonList(1L), 1L);
        assertNotNull(saida);
        assertFalse(saida.isEmpty());
    }

}
