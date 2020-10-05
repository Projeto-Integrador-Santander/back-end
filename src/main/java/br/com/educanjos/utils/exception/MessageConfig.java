package br.com.educanjos.utils.exception;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageConfig {

    public static String getMessage(String codigo){
        Locale locale = new Locale("pt", "BR");
        ResourceBundle messages = ResourceBundle.getBundle("messages/Erros", locale);
        return messages.getString(codigo);
    }
}
