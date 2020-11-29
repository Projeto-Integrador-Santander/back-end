package br.com.educanjos.models.entities;

import br.com.educanjos.models.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("sobrenome")
    private String sobrenome;

    @CPF(message = "CPF inválido.")
    @Column(unique = true)
    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("numero_whatsapp")
    private String telefone;

    @JsonProperty("url_foto")
    private String urlFoto;

    @JsonProperty("sobre")
    private String sobre;

    private Status status;

    public Perfil(){
        this.status = Status.ATIVO;
    }

}
