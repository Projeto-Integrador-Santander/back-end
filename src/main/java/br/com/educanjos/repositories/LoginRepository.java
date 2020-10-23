package br.com.educanjos.repositories;

import br.com.educanjos.models.entities.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {

    Login findByEmail(String email);
    Login findByEmailAndSenha(String email, String senha);
}
