package br.com.educanjos.models.entities;

import br.com.educanjos.models.enums.TipoCadastroPessoa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    @OneToOne(cascade = CascadeType.ALL)
    private Login login;

    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;

    @OneToOne(cascade = CascadeType.ALL)
    private Perfil perfil;

    public Pessoa(Long id) {
        this.id = id;
    }
}
