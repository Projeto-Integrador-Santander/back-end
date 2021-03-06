package br.com.educanjos.facades;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.educanjos.models.enums.TipoCadastroPessoa;
import br.com.educanjos.infra.handler.model.ExceptionEducanjosApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.educanjos.models.entities.Login;
import br.com.educanjos.models.entities.Perfil;
import br.com.educanjos.models.entities.Pessoa;
import br.com.educanjos.repositories.PessoaRepository;

import static br.com.educanjos.utils.ValidationsUtil.*;

@Service
public class PessoaFacade {
	
	@Autowired
	private PessoaRepository repository;

	@Autowired
	private PerfilFacade perfilFacade;

	@Autowired
	private LoginFacade loginFacade;

	public Pessoa newPessoa(Pessoa pessoa, String tipo){
        perfilFacade.findByCPF(pessoa.getPerfil().getCpf());
        loginFacade.findByEmail(pessoa.getLogin().getEmail());
        pessoa.setTipoCadastro(TipoCadastroPessoa.get(tipo));
        validaMateriasEntrada(tipo, pessoa, pessoa);
		return repository.save(pessoa);
	}
	
	public Pessoa editPessoa(Pessoa pessoa, String tipo){
		Optional<Pessoa> entity = repository.findById(pessoa.getId());
		
		Pessoa pessoaBase = entity.get();
		Perfil perfilBase = pessoaBase.getPerfil();
		Perfil perfil = pessoa.getPerfil();
		perfilBase.setNome(perfil.getNome());
		perfilBase.setCpf(perfil.getCpf());
		perfilBase.setSobre(perfil.getSobre());
		perfilBase.setSobrenome(perfil.getSobrenome());
		perfilBase.setTelefone(perfil.getTelefone());
		perfilBase.setUrlFoto(perfil.getUrlFoto());
        validaMateriasEntrada(tipo, pessoa, pessoaBase);
		Login login = pessoaBase.getLogin();
		login.setSenha(pessoa.getLogin().getSenha());
		
		return repository.save(pessoaBase);
	}
	
    public Pessoa getPessoaById(Long id){
        Optional<Pessoa> entity = repository.findById(id);
        verificaIsPresente(entity, id.toString());
        return entity.get();
    }
    
    public Pessoa getPessoaByIdLogin(Long id){
        Pessoa entity = repository.findByLoginId(id);
        verificaIsNull(entity, "VALIDACAO-1", id.toString());
        return entity;
    }
    
    public List<Pessoa> getAllProfessor(){
        List<Pessoa> entities = repository.findByTipoCadastro(TipoCadastroPessoa.PROFESSOR);
        return entities;
    }

    public List<Pessoa> getAllPessoa(){
        List<Pessoa> entities = repository.findAll();
        verificaIsEmpty(entities);
        return entities;
    }

    public void deletePessoa(Long id){
    	Optional<Pessoa> entity = repository.findById(id);
        verificaIsPresente(entity, id.toString());
        repository.deleteById(id);
    }

    private void validaMateriasEntrada(String tipo, Pessoa pessoa, Pessoa pessoaBase){
        if (tipo.equals("ALUNO")){
            pessoaBase.setMaterias(pessoa.getMaterias().stream()
                    .filter(x -> x.getId() != 0).collect(Collectors.toList()));
        }
    }
}
