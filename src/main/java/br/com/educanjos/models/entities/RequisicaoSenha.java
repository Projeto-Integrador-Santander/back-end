package br.com.educanjos.models.entities;

import br.com.educanjos.models.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Getter
@NoArgsConstructor
public class RequisicaoSenha {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private LocalDateTime lastUpdate;
    private Status status;

    public RequisicaoSenha(String email){
        this.lastUpdate = LocalDateTime.now();
        this.email = email;
        this.status = Status.ATIVO;
    }

    public RequisicaoSenha(RequisicaoSenha requisicaoSenha) {
        this.id =  requisicaoSenha.id;
        this.email = requisicaoSenha.email;
        this.lastUpdate = LocalDateTime.now();
        this.status = Status.INATIVO;
    }
}
