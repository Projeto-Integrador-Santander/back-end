package br.com.educanjos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sun.istack.NotNull;

import br.com.educanjos.facades.AgendaFacade;
import br.com.educanjos.models.entities.PessoaAgenda;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("agenda/v1")
@Validated
@AllArgsConstructor
public class PessoaAgendaController {
	
	@Autowired
    private AgendaFacade facade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PessoaAgenda newAgenda(@RequestBody PessoaAgenda agenda) {
        return facade.newAgenda(agenda);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PessoaAgenda getById(@PathVariable("id") @NotNull Long id) {
        return facade.getAgendaById(id);
    }
    
    @GetMapping("/{pessoaId}/{diaSemana}")
    @ResponseStatus(HttpStatus.OK)
    public List<PessoaAgenda> getByPessoaIdAndDiaSemana(@PathVariable("pessoaId") @NotNull Long pessoaId, @PathVariable("diaSemana") @NotNull Long diaSemana) {
        return facade.getByPessoaIdAndDiaSemana(pessoaId, diaSemana);
    }
    
    @GetMapping("aula/{materiasId}/{diaSemana}")
    @ResponseStatus(HttpStatus.OK)
    public List<PessoaAgenda> getByMateriasIdAndDiaSemana(@PathVariable("materiasId") @NotNull List<Long> materiasId, @PathVariable("diaSemana") @NotNull Long diaSemana) {
        return facade.getByMateriasIdAndDiaSemana(materiasId, diaSemana);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PessoaAgenda> getAll() {
        return facade.getAllAgenda();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void atualiza(@PathVariable("id") @NotNull Long id,
                         @RequestBody PessoaAgenda agenda) {
        facade.atualizaAgenda(id, agenda);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable("id") @NotNull Long id) {
        facade.deleteById(id);
    }

}
