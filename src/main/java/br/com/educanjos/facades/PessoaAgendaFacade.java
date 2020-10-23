package br.com.educanjos.facades;

import static br.com.educanjos.utils.ValidationsUtil.verificaIsEmpty;
import static br.com.educanjos.utils.ValidationsUtil.verificaIsPresente;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.educanjos.models.entities.PessoaAgenda;
import br.com.educanjos.repositories.PessoaAgendaRepository;

@Service
public class PessoaAgendaFacade {

	@Autowired
	private PessoaAgendaRepository repository;  

    public PessoaAgenda newPessoaAgenda(PessoaAgenda pessoaAgenda){
    	return repository.save(pessoaAgenda);
    }

    public PessoaAgenda getPessoaAgendaById(Long id){
        Optional<PessoaAgenda> entity = repository.findById(id);
        verificaIsPresente(entity, id.toString());
        return entity.get();
    }

    public List<PessoaAgenda> getAllPessoaAgenda(){
        List<PessoaAgenda> entities = repository.findAll();
        verificaIsEmpty(entities);
        return entities;
    }

    public void deletePessoaAgenda(Long id){
    	Optional<PessoaAgenda> entity = repository.findById(id);
        verificaIsPresente(entity, id.toString());
        repository.deleteById(id);
    }
    
}
