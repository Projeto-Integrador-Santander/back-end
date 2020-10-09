package br.com.educanjos.models.entities;

import br.com.educanjos.models.enums.TipoCadastroPessoa;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class ProfessorMateria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Materia materia;

    @OneToOne(cascade = CascadeType.ALL)
    private Pessoa professor;


}
