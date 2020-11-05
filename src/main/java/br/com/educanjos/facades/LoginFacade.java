package br.com.educanjos.facades;

import br.com.educanjos.models.dto.EmailDTO;
import br.com.educanjos.models.dto.EnvioEmail;
import br.com.educanjos.models.entities.Login;
import br.com.educanjos.models.entities.Perfil;
import br.com.educanjos.models.entities.Pessoa;
import br.com.educanjos.models.entities.RequisicaoSenha;
import br.com.educanjos.models.enums.EnvioEmailAssunto;
import br.com.educanjos.models.enums.Status;
import br.com.educanjos.repositories.LoginRepository;

import br.com.educanjos.repositories.RequisicaoSenhaRepository;
import br.com.educanjos.service.EmailService;
import br.com.educanjos.utils.exception.ExceptionEducanjosApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;
import java.util.List;
import java.util.Optional;

import static br.com.educanjos.utils.ValidationsUtil.*;

@Service
public class LoginFacade {

    @Autowired
    private LoginRepository repository;

    @Autowired
    private RequisicaoSenhaRepository requisicaoSenhaRepository;

    @Autowired
    private PessoaFacade pessoaFacade;

    @Autowired
    private EmailService emailService;

    public Login newLogin(Login login) {
        findByEmail(login.getEmail());
        return repository.save(login);
    }

    public Login getLoginById(Long id) {
        Optional<Login> entity = repository.findById(id);
        verificaIsPresente(entity, id.toString());
        return entity.get();
    }

    public Login getLoginByEmail(String email) {
        Login entity = repository.findByEmail(email);
        verificaIsNull(entity, "VALIDACAO-5", email);
        return entity;
    }

    public Login getLoginByEmailSenha(String email, String senha) {
        Login login = repository.findByEmailAndSenha(email, senha);
        verificaIsNull(login, "VALIDACAO-8", null);
        return login;
    }

    public List<Login> getAllLogin() {
        List<Login> entities = repository.findAll();
        verificaIsEmpty(entities);
        return entities;
    }

    public void atualizaSenha(EmailDTO emailDTO, Long idRequisicao) {
        List<RequisicaoSenha> requisicaoSenhaList = requisicaoSenhaRepository.findByIdAndEmailAndStatus(idRequisicao, emailDTO.getEmail(), Status.ATIVO);
        verificaIsEmpty(requisicaoSenhaList);
        RequisicaoSenha requisicaoSenha = requisicaoSenhaList.stream().findFirst().get();
        verificaIsInactive(requisicaoSenha.getStatus().toString(), "VALIDACAO-9");
        Login login = getLoginByEmail(emailDTO.getEmail());
        login.setSenha(emailDTO.getSenha());
        repository.save(login);
        requisicaoSenhaRepository.save(new RequisicaoSenha(requisicaoSenha));
    }

    public void findByEmail(String email) {
        Login entity = repository.findByEmail(email);
        verificaIsNotNull(entity, "VALIDACAO-7", email);
    }

    public void recuperarSenha(EmailDTO email) {
        Login entity = getLoginByEmail(email.getEmail());
        Pessoa pessoa = pessoaFacade.getPessoaByIdLogin(entity.getId());
        RequisicaoSenha requisicaoSenha = requisicaoSenhaRepository.save(new RequisicaoSenha(entity.getEmail()));
        emailService.enviarEmail(new EnvioEmail(EnvioEmailAssunto.RECUPERACAOSENHA, pessoa, requisicaoSenha));
    }

}
