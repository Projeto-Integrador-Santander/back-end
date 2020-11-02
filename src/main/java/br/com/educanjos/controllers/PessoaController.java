package br.com.educanjos.controllers;

import br.com.educanjos.facades.PessoaFacade;
import br.com.educanjos.models.entities.Pessoa;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("pessoa/v1")
@Validated
@AllArgsConstructor
public class PessoaController {
	@Autowired
    private PessoaFacade facade;

    @PostMapping("{tipo}")
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoa newPessoa(@RequestBody @Valid Pessoa pessoa,
                            @PathVariable("tipo") String tipo) {
        return facade.newPessoa(pessoa, tipo);
    }
    
    @PutMapping("{tipo}")
    @ResponseStatus(HttpStatus.OK)
    public Pessoa editPessoa(@RequestBody Pessoa pessoa,
                            @PathVariable("tipo") String tipo) {
        return facade.editPessoa(pessoa, tipo);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Pessoa getById(@PathVariable("id") @NotNull Long id) {
        return facade.getPessoaById(id);
    }
    
    @GetMapping("login/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Pessoa getByIdLogin(@PathVariable("id") @NotNull Long id) {
        return facade.getPessoaByIdLogin(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Pessoa> getAll() {
        return facade.getAllPessoa();
    }

    @PatchMapping("/inativa/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void inativa(@PathVariable("id") @NotNull Long id) {
        facade.deletePessoa(id);
    }
    
    @GetMapping("/professor")
    @ResponseStatus(HttpStatus.OK)
    public List<Pessoa> getAllProfessor() {
        return facade.getAllProfessor();
    }

}
