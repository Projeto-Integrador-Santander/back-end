package br.com.educanjos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.educanjos.models.entities.PessoaAgenda;

@Repository
public interface PessoaAgendaRepository extends JpaRepository<PessoaAgenda, Long>{
	
	List<PessoaAgenda> getByPessoaIdAndDiaSemana(Long pessoaId, Long diaSemana);
}
