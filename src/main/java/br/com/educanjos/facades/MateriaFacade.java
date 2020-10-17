package br.com.educanjos.facades;

import br.com.educanjos.models.entities.Materia;
import br.com.educanjos.repositories.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import static br.com.educanjos.utils.ValidationsUtil.*;

@Service
public class MateriaFacade {
    @Autowired
    private MateriaRepository repository;

    public Materia newMateria(Materia materia){
        return repository.save(materia);
    }

    public Materia getMateriaById(Long id){
        Optional<Materia> entity = repository.findById(id);
        verificaIsPresente(entity, id.toString());
        return entity.get();
    }

    public List<Materia> getAllMateria(){
        List<Materia> entities = repository.findAll();
        verificaIsEmpty(entities);
        return entities;
    }

    public void deleteMateria(Long id){
        Materia entity = getMateriaById(id);
        repository.deleteById(entity.getId());
    }

    public void verificaExistencia(Long id){
        Optional<Materia> materia = repository.findById(id);
        verificaIsPresente(materia, id.toString() + " em materia");
    }
}
