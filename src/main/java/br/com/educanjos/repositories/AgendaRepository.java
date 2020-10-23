package br.com.educanjos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.educanjos.models.entities.Agenda;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long>{

}
