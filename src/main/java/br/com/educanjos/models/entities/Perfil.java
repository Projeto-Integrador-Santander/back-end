package br.com.educanjos.models.entities;

import br.com.educanjos.models.enums.StatusPerfil;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private String sobrenome;

    @Column(unique = true)
    private String cpf;

    private String telefone;

    private String urlFoto;

    private String sobre;

    private StatusPerfil status;

    public Perfil(){
        this.status = StatusPerfil.ATIVO;
    }
}
