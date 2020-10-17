package br.com.educanjos.repositories;

import br.com.educanjos.models.entities.ProfessorMateria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorMateriaRepository extends JpaRepository<ProfessorMateria, Long> {

}
