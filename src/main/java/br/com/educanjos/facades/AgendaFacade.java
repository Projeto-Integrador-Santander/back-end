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
public class AgendaFacade {
	
	@Autowired
    private PessoaAgendaRepository repository;

    public PessoaAgenda newAgenda(PessoaAgenda Agenda){
        return repository.save(Agenda);
    }

    public PessoaAgenda getAgendaById(Long id){
        Optional<PessoaAgenda> entity = repository.findById(id);
        verificaIsPresente(entity, id.toString());
        return entity.get();
    }
    
    public List<PessoaAgenda> getByPessoaIdAndDiaSemana(Long pessoaId, Long diaSemana){
    	 List<PessoaAgenda> entities = repository.getByPessoaIdAndDiaSemana(pessoaId, diaSemana);
        return entities;
    }

    public List<PessoaAgenda> getAllAgenda(){
        List<PessoaAgenda> entities = repository.findAll();
        verificaIsEmpty(entities);
        return entities;
    }

    public void atualizaAgenda(Long id, PessoaAgenda agenda){
    	PessoaAgenda entity = getAgendaById(id);
        agenda.setId(entity.getId());
        repository.save(entity);
    }
    
    public void deleteById(Long id) {
    	repository.deleteById(id);
    }

}
