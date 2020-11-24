package br.com.educanjos.infra.mail.model;

import br.com.educanjos.models.entities.Pessoa;
import br.com.educanjos.models.entities.RequisicaoSenha;
import br.com.educanjos.models.enums.EnvioEmailAssunto;
import br.com.educanjos.models.enums.TipoCadastroPessoa;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EnvioEmail {
    private String email;
    private String assunto;
    private String corpoEmail;
    private String saudacao;

    private void montaEmail(String textoDefault, String email, String assuntoEmail, String saudacao){
        this.corpoEmail = textoDefault;
        this.email = email;
        this.assunto = assuntoEmail;
        this.saudacao = saudacao;
    }

    public EnvioEmail(EnvioEmailAssunto envioEmailAssunto, Pessoa pessoa, RequisicaoSenha requisicaoSenha) {
        String saudacao = String.format(EnvioEmailAssunto.SAUDACAO_DEFAULT.getDescricao(), pessoa.getPerfil().getNome());
        String assuntoEmail = envioEmailAssunto.getDescricao();
        String email = pessoa.getLogin().getEmail();
        String textoDefault = montaTextoDefault(envioEmailAssunto, pessoa, requisicaoSenha);
        montaEmail(textoDefault, email, assuntoEmail, saudacao);
    }

    private String montaTextoDefault(EnvioEmailAssunto envioEmailAssunto, Pessoa pessoa, RequisicaoSenha requisicaoSenha){
        String textoDefault = "";
        if (envioEmailAssunto.equals(EnvioEmailAssunto.RECUPERACAO_SENHA)){
            String requisicaoId = requisicaoSenha.getToken();
            String tipoCadastro = pessoa.getTipoCadastro().getDescricao();
            textoDefault = String.format(envioEmailAssunto.getTextoDefault(), tipoCadastro.toLowerCase(), requisicaoId);
        }else if(envioEmailAssunto.equals(EnvioEmailAssunto.CADASTRO_EFETUADO)){
            textoDefault = String.format(envioEmailAssunto.getTextoDefault(), pessoa.getPerfil().getNome());
            if (pessoa.getTipoCadastro().equals(TipoCadastroPessoa.ALUNO)){
                textoDefault = textoDefault.concat(EnvioEmailAssunto.CADASTRO_EFETUADO_ALUNO.getDescricao());
            }else {
                textoDefault = textoDefault.concat(EnvioEmailAssunto.CADASTRO_EFETUADO_PROFESSOR.getDescricao());
            }
        }else if (envioEmailAssunto.equals(EnvioEmailAssunto.CADASTRO_EFETUADO)){
            textoDefault = String.format(envioEmailAssunto.getTextoDefault(), requisicaoSenha.getLastUpdate());
        }
        return textoDefault;
    }

    public String getEmail() {
        return this.email;
    }

    public String getAssunto() {
        return this.assunto;
    }

    public String getCorpoEmail() {
        return this.corpoEmail;
    }

    public String getSaudacao() {
        return this.saudacao;
    }
}
