package br.com.educanjos.models.dto;

import lombok.Getter;
import javax.validation.constraints.NotNull;

@Getter
public class EmailDTO {
    private String email;
    private String senha;
    private String token;

}
