package br.com.educanjos.facades;

import static br.com.educanjos.utils.ValidationsUtil.verificaIsEmpty;
import static br.com.educanjos.utils.ValidationsUtil.verificaIsPresente;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.educanjos.models.entities.Agenda;
import br.com.educanjos.repositories.AgendaRepository;

@Service
public class AgendaFacade {
	
	@Autowired
    private AgendaRepository repository;

    public Agenda newAgenda(Agenda Agenda){
        return repository.save(Agenda);
    }

    public Agenda getAgendaById(Long id){
        Optional<Agenda> entity = repository.findById(id);
        verificaIsPresente(entity, id.toString());
        return entity.get();
    }

    public List<Agenda> getAllAgenda(){
        List<Agenda> entities = repository.findAll();
        verificaIsEmpty(entities);
        return entities;
    }

    public void atualizaAgenda(Long id, Agenda agenda){
    	Agenda entity = getAgendaById(id);
        agenda.setId(entity.getId());
        repository.save(entity);
    }

}
