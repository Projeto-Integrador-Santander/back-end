package br.com.educanjos.controllers;

import br.com.educanjos.facades.EnderecoFacade;
import br.com.educanjos.models.entities.Endereco;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("endereco/v1")
@Validated
@AllArgsConstructor
public class EnderecoController {

    @Autowired
    private EnderecoFacade facade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Endereco newEndereco(@RequestBody Endereco Endereco) {
        return facade.newEndereco(Endereco);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Endereco getById(@PathVariable("id") @NotNull Long id) {
        return facade.getEnderecoById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Endereco> getAll() {
        return facade.getAllEndereco();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void atualiza(@PathVariable("id") @NotNull Long id,
                         @RequestBody Endereco endereco) {
        facade.atualizaEndereco(id, endereco);
    }

}
