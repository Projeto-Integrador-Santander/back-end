package br.com.educanjos.models.entities;

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

    @OneToOne //(cascade = CascadeType.PERSIST)
    private Materia materia;

    @OneToOne //(cascade = CascadeType.PERSIST)
    private Pessoa professor;


}
