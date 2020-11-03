package br.com.educanjos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.educanjos.models.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{

	Usuario findByLogin(String login);
}
