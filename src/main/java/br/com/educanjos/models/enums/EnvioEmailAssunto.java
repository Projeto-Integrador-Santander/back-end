package br.com.educanjos.models.enums;

import lombok.Getter;

@Getter
public enum EnvioEmailAssunto {
    RECUPERACAO_SENHA("Recuperação de senha - Educanjos", "Atualize sua senha em http://localhost:4200/%s/esqueci-minha-senha/%s Observação: sua solicitação expira em 30 minutos, após este período será necessário abrir uma nova solicitação."),
    CADASTRO_EFETUADO("Cadastro efetuado com sucesso - Educanjos","Seja bem-vindo(a) ao educanjos. "),
    CADASTRO_EFETUADO_PROFESSOR("Esperamos que sua experiência seja positiva e que consiga passar o máximo de conhecimento possível aos nossos alunos."),
    CADASTRO_EFETUADO_ALUNO("Esperamos que sua experiência seja positiva e que sua aprendizagem seja contínua."),
    SENHA_ALTERADA("Alteração de senha - Educanjos", "A sua senha de acesso ao portal Educanjos foi alterada. Data da alteração: %s (Horário de Brasília - Brasil)."),
    SAUDACAO_DEFAULT("Olá %s,");

    private String descricao;
    private String textoDefault;

    EnvioEmailAssunto(String descricao, String textoDefault) {
        this.descricao = descricao;
        this.textoDefault = textoDefault;
    }

    EnvioEmailAssunto(String descricao) {
        this.descricao = descricao;
    }

}
