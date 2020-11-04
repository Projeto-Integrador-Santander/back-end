package br.com.educanjos.models.enums;

import lombok.Getter;

public enum EnvioEmailAssunto {
    RECUPERACAOSENHA("Recuperação de senha - Educanjos", "Atualize sua senha em http://localhost:4200/%s/esqueci-minha-senha/%s"),
    CONFIRMACAOCADASTRO("Confirme seu cadastro - Educanjos", null);

    @Getter
    private String descricao;
    @Getter
    private String textoDefault;

    EnvioEmailAssunto(String descricao, String textoDefault) {
        this.descricao = descricao;
        this.textoDefault = textoDefault;
    }
}
