package br.com.educanjos.utils.exception;

import br.com.educanjos.utils.config.MessageConfig;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class ExceptionEducanjosApi extends ResponseStatusException {

	private static final long serialVersionUID = 1L;

	private String codErro;

    private String message;

    public ExceptionEducanjosApi(HttpStatus status, String codErro) {
        super(status, (String)null, (Throwable)null);
        this.codErro = codErro;
        this.message = String.format(MessageConfig.getMessage(codErro));
    }

    public ExceptionEducanjosApi(HttpStatus status, String codErro, Object... args) {
        super(status, (String)null, (Throwable)null);
        this.codErro = codErro;
        this.message = String.format(MessageConfig.getMessage(codErro), args);
    }

}
