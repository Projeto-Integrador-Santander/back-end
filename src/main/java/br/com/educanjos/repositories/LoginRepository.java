package br.com.educanjos.repositories;

import br.com.educanjos.models.entities.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {

    Login findByEmail(String email);
    Login findByEmailAndSenha(String email, String senha);
}
