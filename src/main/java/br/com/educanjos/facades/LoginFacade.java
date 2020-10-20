package br.com.educanjos.facades;

import br.com.educanjos.models.entities.Login;
import br.com.educanjos.repositories.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import static br.com.educanjos.utils.ValidationsUtil.*;

@Service
public class LoginFacade {

    @Autowired
    private LoginRepository repository;

    public Login newLogin(Login Login) {
    	
    	Login entity = repository.findByEmail(Login.getEmail());
    	
    	if (entity == null || entity.getId() == 0) {
    		return repository.save(Login);
    	} else {
    		// Solta um erro
    		return null;
    	}
    	
    }

    public Login getLoginById(Long id) {
        Optional<Login> entity = repository.findById(id);
        verificaIsPresente(entity, id.toString());
        return entity.get();
    }

    public Login getLoginByEmail(String email) {
        Login entity = repository.findByEmail(email);
        verificaIsNotNull(entity, "VALIDACAO-5", email);
        return entity;
    }
    
    public Login getLoginByEmailSenha(String email, String senha) {
        Login entity = repository.findByEmail(email);
        verificaIsNotNull(entity, "VALIDACAO-5", email);
        
        if (!entity.getSenha().equals(senha)) {
        	return null; 
        }
        
        return entity;
    }

    public List<Login> getAllLogin() {
        List<Login> entities = repository.findAll();
        verificaIsEmpty(entities);
        return entities;
    }
    
    public void atualizaSenha(String email, String senha){
        Login login = getLoginByEmail(email);
        login.setSenha(senha);
        repository.save(login);
    }
}
