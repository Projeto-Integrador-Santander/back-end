package br.com.educanjos.utils.exception;

import lombok.Getter;

@Getter
public class ResponseException {
    private String codigo;
    private String mensagem;

    public ResponseException(ExceptionEducanjosApi exception) {
        // this.codigo = exception.getCodErro();
    	this.codigo = "100";
        this.mensagem = exception.getMessage();
    }

}
