package br.com.educanjos.models.dto;

import lombok.Getter;
import javax.validation.constraints.NotNull;

@Getter
public class EmailDTO {
    private String email;
    private String senha;
    private String token;

    public String getSenha() {
        return this.senha;
    }

    public String getEmail() {
        return this.email;
    }

    public String getToken() {
        return this.token;
    }
}
