package br.com.educanjos.controllers;

import br.com.educanjos.facades.LoginFacade;
import br.com.educanjos.models.dto.EmailDTO;
import br.com.educanjos.models.dto.EnvioEmail;
import br.com.educanjos.models.entities.Login;
import br.com.educanjos.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("login/v1")
@Validated
@AllArgsConstructor
public class LoginController {

    @Autowired
    private LoginFacade facade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Login newLogin(@RequestBody Login Login) {
        return facade.newLogin(Login);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Login getById(@PathVariable("id") @NotNull Long id) {
        return facade.getLoginById(id);
    }

    @GetMapping("/email/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Login getByEmail(@PathVariable("email") @NotNull String email) {
        return facade.getLoginByEmail(email);
    }
    
    @GetMapping("/{email}/{senha}")
    @ResponseStatus(HttpStatus.OK)
    public Login getByEmailSenha(@PathVariable("email") @NotNull String email, @PathVariable("senha") @NotNull String senha) {
        return facade.getLoginByEmailSenha(email, senha);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Login> getAll() {
        return facade.getAllLogin();
    }

    @PostMapping("atualiza-senha/{idRequisicao}")
    @ResponseStatus(HttpStatus.OK)
    public void atualizaSenha(@RequestBody @Valid EmailDTO email,
                              @PathVariable("idRequisicao") @NotNull Long idRequisicao){
        facade.atualizaSenha(email, idRequisicao);
    }

    @PostMapping("esqueci-senha")
    @ResponseStatus(HttpStatus.OK)
    public void esqueciMinhaSenha(@RequestBody @Valid EmailDTO email){
        facade.recuperarSenha(email);
    }
}
