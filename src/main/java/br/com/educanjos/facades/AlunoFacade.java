package br.com.educanjos.facades;

import static br.com.educanjos.utils.ValidationsUtil.verificaIsEmpty;
import static br.com.educanjos.utils.ValidationsUtil.verificaIsPresente;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.educanjos.models.entities.Aluno;
import br.com.educanjos.repositories.AlunoRepository;

@Service
public class AlunoFacade {
	
	@Autowired
	private AlunoRepository repository;

	public Aluno newAluno(Aluno aluno){
		return repository.save(aluno);
	}

    public Aluno getAlunoById(Long idAluno){
        Optional<Aluno> entity = repository.findById(idAluno);
        verificaIsPresente(entity, "Aluno-1");
        return entity.get();
    }

    public List<Aluno> getAllAluno(){
        List<Aluno> entities = repository.findAll();
        verificaIsEmpty(entities, "Aluno-2");
        return entities;
    }

    public void deleteAluno(Long id){
    	Optional<Aluno> entity = repository.findById(id);
        verificaIsPresente(entity, "Aluno-3");
        repository.deleteById(id);
    }
    
}
