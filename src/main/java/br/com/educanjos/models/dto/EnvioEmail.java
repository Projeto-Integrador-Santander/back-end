package br.com.educanjos.models.dto;

import br.com.educanjos.models.entities.Pessoa;
import br.com.educanjos.models.entities.RequisicaoSenha;
import br.com.educanjos.models.enums.EnvioEmailAssunto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EnvioEmail {
    private String email;
    private String assunto;
    private String corpoEmail;

    public EnvioEmail(EnvioEmailAssunto envioEmailAssunto, Pessoa pessoa, RequisicaoSenha requisicaoSenha) {
        Long requisicaoId = requisicaoSenha.getId();
        String email = pessoa.getLogin().getEmail();
        String tipoCadastro = pessoa.getTipoCadastro().getDescricao();
        String textoDefault = String.format(envioEmailAssunto.getTextoDefault(), tipoCadastro.toLowerCase(), requisicaoId);
        String assuntoEmail = envioEmailAssunto.getDescricao();

        if (envioEmailAssunto.equals(EnvioEmailAssunto.RECUPERACAOSENHA)){
            this.corpoEmail = textoDefault;
            this.email = email;
            this.assunto = assuntoEmail;
        }
    }
}
