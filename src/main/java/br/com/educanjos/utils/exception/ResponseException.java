package br.com.educanjos.utils.exception;

import br.com.educanjos.utils.config.MessageConfig;
import lombok.Getter;

@Getter
public class ResponseException {
    private String codigo;
    private String mensagem;

    public ResponseException(ExceptionEducanjosApi exception) {
        this.codigo = exception.getCodErro();
        this.mensagem = MessageConfig.getMessage(exception.getCodErro());
    }

}
