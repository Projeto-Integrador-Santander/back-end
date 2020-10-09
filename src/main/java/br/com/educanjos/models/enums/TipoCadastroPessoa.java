package br.com.educanjos.models.enums;

import lombok.Getter;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

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
        return null;
    }
}
