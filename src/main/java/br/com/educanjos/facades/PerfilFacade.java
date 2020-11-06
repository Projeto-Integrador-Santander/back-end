package br.com.educanjos.facades;

import br.com.educanjos.models.entities.Perfil;
import br.com.educanjos.repositories.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import static br.com.educanjos.utils.ValidationsUtil.*;

@Service
public class PerfilFacade {
    @Autowired
    private PerfilRepository repository;

    public Perfil newPerfil(Perfil perfil){
        findByCPF(perfil.getCpf());
        return repository.save(perfil);
    }

    public Perfil getPerfilById(Long id){
        Optional<Perfil> entity = repository.findById(id);
        verificaIsPresente(entity, id.toString());
        return entity.get();
    }

    public List<Perfil> getAllPerfil(){
        List<Perfil> entities = repository.findAll();
        verificaIsEmpty(entities);
        return entities;
    }

    public void deletePerfil(Long id){
        Perfil entity = getPerfilById(id);
        verificaIsInactive(entity.getStatus().toString(), null);
        repository.updateInactivePerfil(id);
    }

    public void findByCPF(String cpf){
        Perfil entity = repository.findByCpf(cpf);
        verificaIsNotNull(entity, "VALIDACAO-0", cpf);
    }


}
