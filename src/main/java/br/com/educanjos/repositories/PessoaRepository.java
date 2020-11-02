package br.com.educanjos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.educanjos.models.entities.Pessoa;
import br.com.educanjos.models.enums.TipoCadastroPessoa;

import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	Pessoa findByLoginId(Long id);
	
	List<Pessoa> findByTipoCadastro(TipoCadastroPessoa tipoCadastroPessoa);

}
