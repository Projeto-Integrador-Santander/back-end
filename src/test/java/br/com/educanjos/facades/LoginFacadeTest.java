package br.com.educanjos.facades;

import br.com.educanjos.infra.handler.model.ExceptionEducanjosApi;
import br.com.educanjos.infra.mail.model.EnvioEmail;
import br.com.educanjos.infra.mail.service.RequisicaoService;
import br.com.educanjos.models.dto.EmailDTO;
import br.com.educanjos.models.entities.Login;
import br.com.educanjos.models.entities.Pessoa;
import br.com.educanjos.models.entities.RequisicaoSenha;
import br.com.educanjos.models.enums.Status;
import br.com.educanjos.repositories.LoginRepository;
import br.com.educanjos.templates.EmailDTOTemplate;
import br.com.educanjos.templates.LoginTemplate;
import br.com.educanjos.templates.PessoaTemplate;
import br.com.educanjos.templates.RequisicaoSenhaTemplate;
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
public class LoginFacadeTest {

    @InjectMocks
    private LoginFacade facade;

    @Mock
    private LoginRepository repository;

    @Mock
    private RequisicaoService requisicaoService;

    @Mock
    private PessoaFacade pessoaFacade;

    private static Login loginTemplate;
    private static RequisicaoSenha requisicaoSenhaTemplate;
    private static EmailDTO emailDTOTemplate;
    private static Pessoa pessoaTemplate;

    @Before
    public void init() {
        initMocks(this);
        FixtureFactoryLoader.loadTemplates("br.com.educanjos.templates");
        loginTemplate = Fixture.from(Login.class).gimme(LoginTemplate.LOGIN_VALIDO);
        requisicaoSenhaTemplate = Fixture.from(RequisicaoSenha.class).gimme(RequisicaoSenhaTemplate.REQUISICAO_SENHA_VALIDO);
        emailDTOTemplate = Fixture.from(EmailDTO.class).gimme(EmailDTOTemplate.EMAIL_VALIDO);
        pessoaTemplate = Fixture.from(Pessoa.class).gimme(PessoaTemplate.PESSOA_VALIDO);
    }

    @Test
    public void deveSalvar() {
        when(repository.findByEmail(anyString())).thenReturn(null);
        when(repository.save(any(Login.class))).thenReturn(loginTemplate);
        Login saida = facade.newLogin(loginTemplate);
        assertNotNull(saida);
    }

    @Test
    public void deveSalvarExceptionEmailJaCadastrado() {
        when(repository.findByEmail(anyString())).thenReturn(loginTemplate);
        try {
            facade.newLogin(loginTemplate);
        }catch (ExceptionEducanjosApi ex){
            assertEquals("VALIDACAO-7", ex.getCodErro());
            assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
            assertNotNull(ex.getMessage());
        }
    }

    @Test
    public void deveBuscarPorId() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(loginTemplate));
        Login saida = facade.getLoginById(1L);
        assertNotNull(saida);
    }

    @Test
    public void deveBuscarPorIdExceptionNaoExistente() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        try {
            facade.getLoginById(1L);
        }catch (ExceptionEducanjosApi ex){
            assertEquals("VALIDACAO-1", ex.getCodErro());
            assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
            assertNotNull(ex.getMessage());
        }
    }

    @Test
    public void deveBuscarPorEmail() {
        when(repository.findByEmail(anyString())).thenReturn(loginTemplate);
        Login saida = facade.getLoginByEmail("email@teste.com");
        assertNotNull(saida);
    }

    @Test
    public void deveBuscarPorEmailExceptionNaoExistente() {
        when(repository.findByEmail(anyString())).thenReturn(null);
        try {
            facade.getLoginByEmail("email@teste.com");
        }catch (ExceptionEducanjosApi ex){
            assertEquals("VALIDACAO-5", ex.getCodErro());
            assertEquals( HttpStatus.NOT_FOUND, ex.getStatus());
            assertNotNull(ex.getMessage());
        }
    }

    @Test
    public void deveBuscarPorEmailAndSenha() {
        when(repository.findByEmailAndSenha(anyString(), anyString())).thenReturn(loginTemplate);
        Login saida = facade.getLoginByEmailSenha("email@teste.com", "senha");
        assertNotNull(saida);
    }

    @Test
    public void deveBuscarPorEmailAndSenhaExceptionNaoExistente() {
        when(repository.findByEmailAndSenha(anyString(), anyString())).thenReturn(null);
        try {
            facade.getLoginByEmailSenha("email@teste.com", "senha");
        }catch (ExceptionEducanjosApi ex){
            assertEquals("VALIDACAO-8", ex.getCodErro());
            assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
            assertNotNull(ex.getMessage());
        }
    }

    @Test
    public void deveBuscarTodos() {
        when(repository.findAll()).thenReturn(Collections.singletonList(loginTemplate));
        List<Login> saida = facade.getAllLogin();
        assertNotNull(saida);
        assertFalse(saida.isEmpty());
    }

    @Test
    public void deveBuscarTodosExceptionNenhumRegistroEncontrado() {
        when(repository.findAll()).thenReturn(Collections.EMPTY_LIST);
        try {
            facade.getAllLogin();
        }catch (ExceptionEducanjosApi ex){
            assertEquals("VALIDACAO-2", ex.getCodErro());
            assertEquals(HttpStatus.NO_CONTENT, ex.getStatus());
            assertNotNull(ex.getMessage());
        }
    }

    @Test
    public void deveAtualizarSenha() {
        when(requisicaoService.buscaPorToken(anyString())).thenReturn(requisicaoSenhaTemplate);
        when(repository.findByEmail(anyString())).thenReturn(loginTemplate);
        when(repository.save(any(Login.class))).thenReturn(loginTemplate);
        when(pessoaFacade.getPessoaByIdLogin(anyLong())).thenReturn(pessoaTemplate);
        doNothing().when(requisicaoService).atualizaStatusRequisicao(any(), any(Status.class));
        EnvioEmail saida = facade.atualizaSenha(emailDTOTemplate);
        assertNotNull(saida);
        assertEquals("Alteração de senha - Educanjos", saida.getAssunto() );
    }

    @Test
    public void deveRecuperarSenha() {
        when(requisicaoService.geraRequisicaoSenha(anyString())).thenReturn(requisicaoSenhaTemplate);
        when(repository.findByEmail(anyString())).thenReturn(loginTemplate);
        when(pessoaFacade.getPessoaByIdLogin(anyLong())).thenReturn(pessoaTemplate);
        EnvioEmail saida = facade.recuperarSenha(emailDTOTemplate);
        assertNotNull(saida);
        assertEquals("Recuperação de senha - Educanjos", saida.getAssunto() );
    }

}
