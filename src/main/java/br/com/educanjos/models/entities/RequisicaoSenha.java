package br.com.educanjos.models.entities;

import br.com.educanjos.models.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    private String token;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime lastUpdate;

    private LocalDateTime dataExpiracao;

    private Status status;

    public RequisicaoSenha(String email, String token){
        this.lastUpdate = LocalDateTime.now();
        this.email = email;
        this.status = Status.ATIVO;
        this.token = token;
        this.dataExpiracao = this.lastUpdate.plusMinutes(30L);
    }

    public RequisicaoSenha(RequisicaoSenha requisicaoSenha, Status status) {
        this.id =  requisicaoSenha.id;
        this.email = email;
        this.token = requisicaoSenha.token;
        this.lastUpdate = LocalDateTime.now();
        this.status = status;
        this.dataExpiracao = requisicaoSenha.dataExpiracao;
    }

}
