package br.com.educanjos.infra.mail.service;

import br.com.educanjos.infra.mail.model.EnvioEmail;

public interface EmailService {

    public void enviarEmail(EnvioEmail envioEmail);

}