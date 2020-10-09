package br.com.educanjos.models.dto;

import br.com.educanjos.models.enums.TipoCadastroPessoa;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaEntrada {

    private String tipoCadastro;
    private Long login;
    private Long endereco;
    private Long perfil;

    public PessoaEntrada(String tipoCadastro, Long login, Long endereco, Long perfil) {
        this.tipoCadastro = tipoCadastro;
        this.login = login;
        this.endereco = endereco;
        this.perfil = perfil;
    }
}
