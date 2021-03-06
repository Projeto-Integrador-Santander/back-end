package br.com.educanjos.models.enums;

import br.com.educanjos.infra.handler.model.ExceptionEducanjosApi;
import lombok.Getter;
import org.springframework.http.HttpStatus;

public enum TipoCadastroPessoa {
    ALUNO("ALUNO"),
    PROFESSOR("PROFESSOR");

    @Getter
    private String descricao;


    TipoCadastroPessoa(String descricao) {
        this.descricao = descricao;
    }

    public static TipoCadastroPessoa get(String find) {
        for (TipoCadastroPessoa enumGet : TipoCadastroPessoa.values()){
            if (enumGet.descricao.equalsIgnoreCase(find)){
                return enumGet;
            }
        }
        throw new ExceptionEducanjosApi(HttpStatus.BAD_REQUEST, "VALIDATION-6", "tipo", TipoCadastroPessoa.values());
    }

    public String getDescricao() {
        return this.descricao;
    }
}
