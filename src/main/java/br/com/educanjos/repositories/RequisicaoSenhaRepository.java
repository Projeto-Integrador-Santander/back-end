package br.com.educanjos.repositories;

import br.com.educanjos.models.entities.RequisicaoSenha;
import br.com.educanjos.models.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequisicaoSenhaRepository extends JpaRepository<RequisicaoSenha, Long> {

    List<RequisicaoSenha> findByIdAndEmailAndStatus(Long idRequisicao, String email, Status ativo);
}
