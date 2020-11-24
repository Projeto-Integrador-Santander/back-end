package br.com.educanjos.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Email(message = "Email inválido.")
    @Column(unique = true)
    private String email;

    private String senha;	
	
    public Login(Long id) {
        this.id = id;
    }

	public String getEmail() {
    	return this.email;
	}

	public String getSenha() {
    	return this.senha;
	}

	public void setSenha(String senha) {
    	this.senha = senha;
	}

    public Long getId() {
        return this.id;
    }
}
