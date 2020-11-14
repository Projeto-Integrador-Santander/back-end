package br.com.educanjos.service;

import br.com.educanjos.models.entities.RequisicaoSenha;
import br.com.educanjos.models.enums.Status;

public interface RequisicaoService {

    public RequisicaoSenha geraRequisicaoSenha(String email);

    public RequisicaoSenha buscaPorToken(String token);

    void atualizaStatusRequisicao(RequisicaoSenha requisicaoSenha, Status status);
}
