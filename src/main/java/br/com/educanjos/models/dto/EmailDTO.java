package br.com.educanjos.models.dto;

import lombok.Getter;
import javax.validation.constraints.NotNull;

@Getter
public class EmailDTO {
    @NotNull
    private String email;
    private String senha;

}
