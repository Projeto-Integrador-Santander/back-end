package br.com.educanjos.facades;

import br.com.educanjos.infra.handler.model.ExceptionEducanjosApi;
import br.com.educanjos.models.entities.Pessoa;
import br.com.educanjos.models.enums.TipoCadastroPessoa;
import br.com.educanjos.repositories.PessoaRepository;
import br.com.educanjos.templates.PessoaTemplate;
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
public class PessoaFacadeTest {

    @InjectMocks
    private PessoaFacade facade;

    @Mock
    private PessoaRepository repository;

    @Mock
    private PerfilFacade perfilFacade;

    @Mock
    private LoginFacade loginFacade;

    private static Pessoa pessoaTemplate;

    @Before
    public void init() {
        initMocks(this);
        FixtureFactoryLoader.loadTemplates("br.com.educanjos.templates");
        pessoaTemplate = Fixture.from(Pessoa.class).gimme(PessoaTemplate.PESSOA_VALIDO);
    }

    @Test
    public void deveSalvarPessoa() {
        doNothing().when(loginFacade).findByEmail(anyString());
        doNothing().when(perfilFacade).findByCPF(anyString());
        when(repository.save(any(Pessoa.class))).thenReturn(pessoaTemplate);
        Pessoa saida = facade.newPessoa(pessoaTemplate, "ALUNO");
        assertNotNull(saida);
    }

    @Test
    public void deveBuscarPessoa() {
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(pessoaTemplate));
        Pessoa saida = facade.getPessoaById(1L);
        assertNotNull(saida);
    }

    @Test
    public void deveBuscarPessoaException() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        try {
            facade.getPessoaById(1L);
        } catch (ExceptionEducanjosApi ex) {
            assertEquals("VALIDACAO-1", ex.getCodErro());
            assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
            assertNotNull(ex.getMessage());
        }
    }

    @Test
    public void deveBuscarPessoaByIdLogin() {
        when(repository.findByLoginId(anyLong())).thenReturn(pessoaTemplate);
        Pessoa saida = facade.getPessoaByIdLogin(1L);
        assertNotNull(saida);
    }

    @Test
    public void deveBuscarPessoaByIdLoginException() {
        when(repository.findByLoginId(anyLong())).thenReturn(null);
        try {
            facade.getPessoaByIdLogin(1L);
        } catch (ExceptionEducanjosApi ex) {
            assertEquals("VALIDACAO-1", ex.getCodErro());
            assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
            assertNotNull(ex.getMessage());
        }
    }

    @Test
    public void deveBuscarTodasPessoaProfessor() {
        when(repository.findByTipoCadastro(any())).thenReturn(Collections.singletonList(pessoaTemplate));
        List<Pessoa> saida = facade.getAllProfessor();
        assertNotNull(saida);
        assertFalse(saida.isEmpty());
    }

    @Test
    public void deveBuscarTodasPessoa() {
        when(repository.findAll()).thenReturn(Collections.singletonList(pessoaTemplate));
        List<Pessoa> saida = facade.getAllPessoa();
        assertNotNull(saida);
        assertFalse(saida.isEmpty());
    }

    @Test
    public void deveBuscarTodasPessoaException() {
        when(repository.findAll()).thenReturn(Collections.EMPTY_LIST);
        try {
            facade.getAllPessoa();
        } catch (ExceptionEducanjosApi ex) {
            assertEquals("VALIDACAO-2", ex.getCodErro());
            assertEquals(HttpStatus.NO_CONTENT, ex.getStatus());
            assertNotNull(ex.getMessage());
        }
    }

    @Test
    public void deveDeletarPessoa() {
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(pessoaTemplate));
        doNothing().when(repository).deleteById(anyLong());
        try {
            facade.deletePessoa(1L);
        } catch (Exception ex) {
            fail();
        }
    }

    @Test
    public void deveAtualizarPessoa() {
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(pessoaTemplate));
        when(repository.save(any(Pessoa.class))).thenReturn(pessoaTemplate);
        Pessoa saida = facade.editPessoa(pessoaTemplate, "ALUNO");
        assertNotNull(saida);
    }

    @Test
    public void deveAtualizarPessoaProfessor() {
        pessoaTemplate.setTipoCadastro(TipoCadastroPessoa.PROFESSOR);
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(pessoaTemplate));
        when(repository.save(any(Pessoa.class))).thenReturn(pessoaTemplate);
        Pessoa saida = facade.editPessoa(pessoaTemplate, "PROFESSOR");
        assertNotNull(saida);
    }
}
