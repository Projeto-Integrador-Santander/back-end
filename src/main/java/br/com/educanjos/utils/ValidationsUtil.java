package br.com.educanjos.utils;

import br.com.educanjos.utils.exception.ExceptionEducanjosApi;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidationsUtil {
    public static void verificaIsPresente(@NonNull Optional<?> optional, @NonNull String codErro){
        if (!optional.isPresent())
            throw new ExceptionEducanjosApi(HttpStatus.NOT_FOUND, codErro);
    }

    public static void verificaIsEmpty(List<?> list, @NonNull String codErro){
        if (Objects.isNull(list) || list.isEmpty())
            throw new ExceptionEducanjosApi(HttpStatus.NO_CONTENT, codErro);
    }

    public static void verificaIsNull(Object object, @NonNull String codErro){
        if (Objects.isNull(object))
            throw new ExceptionEducanjosApi(HttpStatus.BAD_REQUEST, codErro);
    }

    public static void verificaIsNotNull(Object object, @NonNull String codErro){
        if (!Objects.isNull(object))
            throw new ExceptionEducanjosApi(HttpStatus.BAD_REQUEST, codErro);
    }

    public static void verificaIsInactive(@NonNull String status, @NonNull String codErro){
        if (status.equals("INATIVO"))
            throw new ExceptionEducanjosApi(HttpStatus.CONFLICT, codErro);
    }

}
