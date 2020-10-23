package br.com.educanjos.models.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PessoaAgenda {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@OneToOne
	private Long idLogin;
	
	@OneToOne
	private Long idPessoa;
	
	@OneToOne
	private Long idEndereço;

}
