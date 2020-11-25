package br.com.educanjos.models.entities;

import br.com.educanjos.models.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class RequisicaoSenha {
    @Id
    private String token;

    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime lastUpdate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataExpiracao;

    private Status status;

    public RequisicaoSenha(String email, String token) {
        this.token = token;
        this.lastUpdate = LocalDateTime.now();
        this.email = email;
        this.status = Status.ATIVO;
        this.dataExpiracao = this.lastUpdate.plusMinutes(30L);
    }

    public RequisicaoSenha(RequisicaoSenha requisicaoSenha, Status status) {
        this.token = requisicaoSenha.token;
        this.email = requisicaoSenha.email;
        this.lastUpdate = LocalDateTime.now();
        this.status = status;
        this.dataExpiracao = requisicaoSenha.dataExpiracao;
    }

}
