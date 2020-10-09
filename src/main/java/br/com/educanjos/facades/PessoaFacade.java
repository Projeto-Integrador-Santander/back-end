package br.com.educanjos.facades;

import static br.com.educanjos.utils.ValidationsUtil.verificaIsEmpty;
import static br.com.educanjos.utils.ValidationsUtil.verificaIsPresente;

import java.util.List;
import java.util.Optional;

import br.com.educanjos.models.dto.PessoaEntrada;
import br.com.educanjos.models.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.educanjos.models.entities.Pessoa;
import br.com.educanjos.repositories.PessoaRepository;

@Service
public class PessoaFacade {
	
	@Autowired
	private PessoaRepository repository;

	public Pessoa newPessoa(PessoaEntrada entrada){
        Pessoa pessoa = Mapper.entradaToEntity(entrada);
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
    
}
