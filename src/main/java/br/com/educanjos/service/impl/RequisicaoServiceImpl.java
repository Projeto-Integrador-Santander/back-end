package br.com.educanjos.service.impl;

import br.com.educanjos.models.entities.RequisicaoSenha;
import br.com.educanjos.models.enums.Status;
import br.com.educanjos.repositories.RequisicaoSenhaRepository;
import br.com.educanjos.service.RequisicaoService;
import br.com.educanjos.utils.exception.ExceptionEducanjosApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import static br.com.educanjos.utils.ValidationsUtil.*;

@Service
public class RequisicaoServiceImpl implements RequisicaoService {

    @Autowired
    private RequisicaoSenhaRepository requisicaoSenhaRepository;

    @Override
    public RequisicaoSenha geraRequisicaoSenha(String email) {
        String token = geraTokenRandom();
        Boolean exists = true;

        while (exists) {
            exists = !Objects.isNull(requisicaoSenhaRepository.findByToken(token));
            token = geraTokenRandom();
        }
        return requisicaoSenhaRepository.save(new RequisicaoSenha(email, token));
    }

    @Override
    public RequisicaoSenha buscaPorToken(String token) {
        RequisicaoSenha requisicaoSenha = requisicaoSenhaRepository.findByToken(token);
        verificaIsNull(requisicaoSenha, "VALIDACAO-10", null);
        verificaTokenValido(requisicaoSenha);
        return requisicaoSenha;
    }

    @Override
    public void atualizaStatusRequisicao(RequisicaoSenha requisicaoSenha, Status status) {
        requisicaoSenhaRepository.save(new RequisicaoSenha(requisicaoSenha, status));
    }

    private String geraTokenRandom() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    private void verificaTokenValido(RequisicaoSenha requisicaoSenha){
        Boolean isBefore = LocalDateTime.now().isBefore(requisicaoSenha.getDataExpiracao());
        verificaIsInactiveOuCancelado(requisicaoSenha.getStatus().toString(), "VALIDACAO-9");
        if (!isBefore){
            atualizaStatusRequisicao(requisicaoSenha, Status.CANCELADO);
            throw new ExceptionEducanjosApi(HttpStatus.BAD_REQUEST, "VALIDACAO-11");
        }
    }
}
