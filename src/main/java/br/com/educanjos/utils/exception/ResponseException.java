package br.com.educanjos.utils.exception;

import lombok.Getter;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Getter
public class ResponseException {
    private String codigo;
    private String mensagem;

    public ResponseException(ExceptionEducanjosApi exception) {
        this.codigo = exception.getCodErro();
        this.mensagem = exception.getMessage();
    }

    public ResponseException(MethodArgumentNotValidException exception) {
        this.codigo = "VALIDATION";
        this.mensagem = trataMensagemValidacao(exception.getMessage());
    }

    private String trataMensagemValidacao(String mensagem){
        String result = "";
        String resultDefault = "Internal Server Error.";
        if (mensagem.contains("cpf")){
            result = "CPF inválido";
        }else if (mensagem.contains("email")){
            result = "Email inválido";
        }
        return result != "" ? result : resultDefault;
    }
}
