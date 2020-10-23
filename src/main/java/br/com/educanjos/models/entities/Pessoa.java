package br.com.educanjos.models.entities;

import br.com.educanjos.models.enums.TipoCadastroPessoa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
   
    private TipoCadastroPessoa tipoCadastro;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Login login;

    @ManyToMany
    private List<Materia> materias;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Perfil perfil;
    
    public Pessoa(Long id) {
        this.id = id;
    }
}
