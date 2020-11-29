package br.com.educanjos.models.entities;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PessoaAgenda {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long pessoaId;

	private Long materiaId;

	private Long diaSemana;

	private Boolean voluntario;

	private BigDecimal valor;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	private LocalTime inicio;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	private LocalTime fim;

}
