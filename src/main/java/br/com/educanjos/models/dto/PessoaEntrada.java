package br.com.educanjos.models.dto;

import br.com.educanjos.models.entities.Login;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class PessoaEntrada {

    private String tipoCadastro;
    private Long login;
    private Long perfil;
    private List<Long> materias = new ArrayList<>();

    public PessoaEntrada(String tipoCadastro, Long login, Long perfil, List<Long> materias) {
        this.tipoCadastro = tipoCadastro;
        this.login = login;
        this.perfil = perfil;
        this.materias = materias;
    }

    public Long getLogin() {
        return this.login;
    }

    public Long getPerfil() {
        return this.perfil;
    }

    public String getTipoCadastro() {
        return this.tipoCadastro;
    }

    public List<Long> getMaterias() {
        return this.materias;
    }
}
