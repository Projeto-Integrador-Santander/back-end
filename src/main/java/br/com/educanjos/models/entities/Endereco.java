package br.com.educanjos.models.entities;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String cep;

    private String logradouro;

    private Integer numero;

    private String bairro;

    private String cidade;

    private String uf;

    public Endereco(Long id) {
        this.id = id;
    }
}
