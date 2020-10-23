package br.com.educanjos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
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
import br.com.educanjos.models.entities.Agenda;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("agenda/v1")
@Validated
@AllArgsConstructor
public class AgendaController {
	
	@Autowired
    private AgendaFacade facade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Agenda newAgenda(@RequestBody Agenda Agenda) {
        return facade.newAgenda(Agenda);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Agenda getById(@PathVariable("id") @NotNull Long id) {
        return facade.getAgendaById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Agenda> getAll() {
        return facade.getAllAgenda();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void atualiza(@PathVariable("id") @NotNull Long id,
                         @RequestBody Agenda Agenda) {
        facade.atualizaAgenda(id, Agenda);
    }

}
