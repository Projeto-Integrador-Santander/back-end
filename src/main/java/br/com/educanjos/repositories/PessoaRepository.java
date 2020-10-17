package br.com.educanjos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.educanjos.models.entities.Pessoa;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	
}
