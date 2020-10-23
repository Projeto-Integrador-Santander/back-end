package br.com.educanjos.facades;

import br.com.educanjos.models.entities.ProfessorMateria;
import br.com.educanjos.repositories.ProfessorMateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import static br.com.educanjos.utils.ValidationsUtil.verificaIsEmpty;
import static br.com.educanjos.utils.ValidationsUtil.verificaIsPresente;

@Service
public class ProfessorMateriaFacade {
	
	@Autowired
	private ProfessorMateriaRepository repository;
    @Autowired
    private MateriaFacade materiaFacade;
    @Autowired
    private PessoaFacade pessoaFacade;


    public ProfessorMateria newProfessorMateria(ProfessorMateria professor){
	    pessoaFacade.verificaExistencia(professor.getProfessor().getId());
        materiaFacade.verificaExistencia(professor.getMateria().getId());
		return repository.save(professor);
	}

    public ProfessorMateria getProfessorMateriaById(Long id){
        Optional<ProfessorMateria> entity = repository.findById(id);
        verificaIsPresente(entity, id.toString());
        return entity.get();
    }

    public List<ProfessorMateria> getAllProfessorMateria(){
        List<ProfessorMateria> entities = repository.findAll();
        verificaIsEmpty(entities);
        return entities;
    }

    public void deleteProfessorMateria(Long id){
    	Optional<ProfessorMateria> entity = repository.findById(id);
        verificaIsPresente(entity, id.toString());
        repository.deleteById(id);
    }
    
}
