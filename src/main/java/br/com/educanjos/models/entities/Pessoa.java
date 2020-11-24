package br.com.educanjos.models.entities;

import br.com.educanjos.models.enums.TipoCadastroPessoa;
import io.swagger.models.Contact;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import javax.persistence.*;
import javax.validation.Valid;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
   
    private TipoCadastroPessoa tipoCadastro;

    @Valid
    @OneToOne(cascade = CascadeType.PERSIST)
    private Login login;

    @ManyToMany
    private List<Materia> materias;

    @Valid
    @OneToOne(cascade = CascadeType.PERSIST)
    private Perfil perfil;

    //public Pessoa(Long id) {
    //    this.id = id;
    //}

    //public Pessoa() {
    //}

    public Perfil getPerfil() {
        return this.perfil;
    }

    public Login getLogin() {
        return this.login;
    }

    public void setTipoCadastro(TipoCadastroPessoa tipoCadastroPessoa) {
        this.tipoCadastro = tipoCadastroPessoa;
    }

    public Long getId() {
        return this.id;
    }

    public List<Materia> getMaterias() {
        return this.materias;
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
    }

    public TipoCadastroPessoa getTipoCadastro() {
        return this.tipoCadastro;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
}
