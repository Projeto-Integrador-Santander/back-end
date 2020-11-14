package br.com.educanjos.repositories;

import br.com.educanjos.models.entities.RequisicaoSenha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequisicaoSenhaRepository extends JpaRepository<RequisicaoSenha, Long> {

    RequisicaoSenha findByToken(String token);

}
