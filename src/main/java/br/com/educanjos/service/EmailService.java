package br.com.educanjos.service;

import br.com.educanjos.models.dto.EnvioEmail;

public interface EmailService {

    public void enviarEmail(EnvioEmail envioEmail);

}