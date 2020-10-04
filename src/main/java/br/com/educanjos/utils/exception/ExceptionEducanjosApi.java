package br.com.educanjos.utils.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ExceptionEducanjosApi extends ResponseStatusException {

    @Getter
    private String codErro;

    public ExceptionEducanjosApi(HttpStatus status, String codErro) {
        super(status, (String)null, (Throwable)null);
        this.codErro = codErro;
    }

}
