package br.com.educanjos.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Login login;

    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;

    @OneToOne(cascade = CascadeType.ALL)
    private Perfil perfil;
}
