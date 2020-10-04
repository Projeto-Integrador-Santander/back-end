package br.com.educanjos.controllers;

import br.com.educanjos.models.entities.Perfil;
import br.com.educanjos.facades.PerfilFacade;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("perfil/v1")
@Validated
@AllArgsConstructor
public class PerfilController {

    @Autowired
    private PerfilFacade facade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Perfil newPerfil(@RequestBody Perfil perfil) {
        return facade.newPerfil(perfil);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Perfil getById(@PathVariable("id") @NotNull Long id) {
        return facade.getPerfilById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Perfil> getAll() {
        return facade.getAllPerfil();
    }

    @PatchMapping("/inativa/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void inativa(@PathVariable("id") @NotNull Long id) {
        facade.deletePerfil(id);
    }

}
