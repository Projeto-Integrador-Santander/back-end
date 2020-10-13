package br.com.educanjos.facades;

import br.com.educanjos.models.entities.Materia;
import br.com.educanjos.repositories.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import static br.com.educanjos.utils.ValidationsUtil.verificaIsPresente;

@Service
public class MateriaFacade {
    @Autowired
    private MateriaRepository repository;

    public void verificaExistencia(Long id){
        Optional<Materia> materia = repository.findById(id);
        verificaIsPresente(materia, id.toString() + " em materia");
    }
}
