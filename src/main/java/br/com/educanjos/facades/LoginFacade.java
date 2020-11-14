package br.com.educanjos.facades;

import br.com.educanjos.models.dto.EmailDTO;
import br.com.educanjos.infra.mail.model.EnvioEmail;
import br.com.educanjos.models.entities.Login;
import br.com.educanjos.models.entities.Pessoa;
import br.com.educanjos.models.entities.RequisicaoSenha;
import br.com.educanjos.models.enums.EnvioEmailAssunto;
import br.com.educanjos.models.enums.Status;
import br.com.educanjos.repositories.LoginRepository;

import br.com.educanjos.infra.mail.service.RequisicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static br.com.educanjos.utils.ValidationsUtil.*;

@Service
public class LoginFacade {

    @Autowired
    private LoginRepository repository;

    @Autowired
    private RequisicaoService requisicaoService;

    @Autowired
    private PessoaFacade pessoaFacade;

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

    public EnvioEmail atualizaSenha(EmailDTO emailDTO) {
        RequisicaoSenha requisicaoSenha = requisicaoService.buscaPorToken(emailDTO.getToken());
        Login login = getLoginByEmail(emailDTO.getEmail());
        login.setSenha(emailDTO.getSenha());
        repository.save(login);
        requisicaoService.atualizaStatusRequisicao(requisicaoSenha, Status.INATIVO);
        return new EnvioEmail(EnvioEmailAssunto.RECUPERACAO_SENHA, pessoaFacade.getPessoaByIdLogin(login.getId()), null);
    }

    public void findByEmail(String email) {
        Login entity = repository.findByEmail(email);
        verificaIsNotNull(entity, "VALIDACAO-7", email);
    }

    public EnvioEmail recuperarSenha(EmailDTO email) {
        Login entity = getLoginByEmail(email.getEmail());
        Pessoa pessoa = pessoaFacade.getPessoaByIdLogin(entity.getId());
        return new EnvioEmail(EnvioEmailAssunto.RECUPERACAO_SENHA, pessoa, requisicaoService.geraRequisicaoSenha(email.getEmail()));
    }
    


}
