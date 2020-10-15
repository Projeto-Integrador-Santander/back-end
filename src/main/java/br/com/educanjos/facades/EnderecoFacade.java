package br.com.educanjos.facades;

import br.com.educanjos.models.entities.Endereco;
import br.com.educanjos.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import static br.com.educanjos.utils.ValidationsUtil.*;

@Service
public class EnderecoFacade {
    @Autowired
    private EnderecoRepository repository;

    public Endereco newEndereco(Endereco Endereco){
        return repository.save(Endereco);
    }

    public Endereco getEnderecoById(Long id){
        Optional<Endereco> entity = repository.findById(id);
        verificaIsPresente(entity, id.toString());
        return entity.get();
    }

    public List<Endereco> getAllEndereco(){
        List<Endereco> entities = repository.findAll();
        verificaIsEmpty(entities);
        return entities;
    }

    public void atualizaEndereco(Long id, Endereco endereco){
        Endereco entity = getEnderecoById(id);
        endereco.setId(entity.getId());
        repository.save(entity);
    }

}
