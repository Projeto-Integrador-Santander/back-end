package br.com.educanjos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.educanjos.models.entities.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{
	
}
