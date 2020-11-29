package br.com.educanjos.models.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class PessoaEntrada {

    private String tipoCadastro;
    private Long login;
    private Long perfil;
    private List<Long> materias;

    public PessoaEntrada(String tipoCadastro, Long login, Long perfil, List<Long> materias) {
        this.tipoCadastro = tipoCadastro;
        this.login = login;
        this.perfil = perfil;
        this.materias = materias;
    }

}
