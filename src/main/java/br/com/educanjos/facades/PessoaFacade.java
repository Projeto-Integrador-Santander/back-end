package br.com.educanjos.facades;

import java.util.List;
import java.util.Optional;

import br.com.educanjos.models.dto.PessoaEntrada;
import br.com.educanjos.models.enums.TipoCadastroPessoa;
import br.com.educanjos.models.mapper.Mapper;
import br.com.educanjos.utils.exception.ExceptionEducanjosApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.educanjos.models.entities.Pessoa;
import br.com.educanjos.repositories.PessoaRepository;

import static br.com.educanjos.utils.ValidationsUtil.*;

@Service
public class PessoaFacade {
	
	@Autowired
	private PessoaRepository repository;

	public Pessoa newPessoa(Pessoa pessoa, String tipo){
        pessoa.setTipoCadastro(TipoCadastroPessoa.get(tipo));
		return repository.save(pessoa);
	}

    public Pessoa getPessoaById(Long id){
        Optional<Pessoa> entity = repository.findById(id);
        verificaIsPresente(entity, id.toString());
        return entity.get();
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

    public void verificaExistencia(Long id){
	    Optional<Pessoa> pessoa = repository.findById(id);
	    verificaIsPresente(pessoa, id.toString() + " em professor");
	    if (!TipoCadastroPessoa.PROFESSOR.equals(pessoa.get().getTipoCadastro())){
            throw new ExceptionEducanjosApi(HttpStatus.CONFLICT, "VALIDACAO-4");
        }
    }
}
