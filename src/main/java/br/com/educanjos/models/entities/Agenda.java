package br.com.educanjos.models.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Agenda {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	private String dataInicioAula;
	
	private String tempoAula;
	
	private Double valorAula;
	
	private Long status;
	
	private String emailContratante;
	
	private String assuntoAbordado;

	@OneToOne(cascade = CascadeType.PERSIST)
	private Long idCadProfessor;
	
	@ManyToOne
	private Long idMateria;
	
	public Agenda(Long id) {
		this.id = id;
	}
	
}
