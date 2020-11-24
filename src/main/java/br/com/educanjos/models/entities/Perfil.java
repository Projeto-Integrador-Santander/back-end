package br.com.educanjos.models.entities;

import br.com.educanjos.models.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("sobrenome")
    private String sobrenome;

    @CPF(message = "CPF inválido.")
    @Column(unique = true)
    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("numero_whatsapp")
    private String telefone;

    @JsonProperty("url_foto")
    private String urlFoto;

    @JsonProperty("sobre")
    private String sobre;

    private Status status;

    public Perfil(){
        this.status = Status.ATIVO;
    }

    public Perfil(Long id){
        this.id = id;
    }

    public String getCpf() {
        return this.cpf;
    }

    public String getNome() {
        return this.nome;
    }

    public String getSobre() {
        return this.sobre;
    }

    public String getSobrenome() {
        return this.sobrenome;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public String getUrlFoto() {
        return this.urlFoto;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setSobre(String sobre) {
        this.sobre = sobre;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }
}
