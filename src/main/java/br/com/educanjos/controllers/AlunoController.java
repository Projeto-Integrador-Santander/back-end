package br.com.educanjos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sun.istack.NotNull;

import br.com.educanjos.facades.AlunoFacade;
import br.com.educanjos.models.entities.Aluno;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("aluno/v1")
@Validated
@AllArgsConstructor
public class AlunoController {
	@Autowired
    private AlunoFacade facade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Aluno newAluno(@RequestBody Aluno aluno) {
        return facade.newAluno(aluno);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Aluno getById(@PathVariable("id") @NotNull Long id) {
        return facade.getAlunoById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Aluno> getAll() {
        return facade.getAllAluno();
    }

    @PatchMapping("/inativa/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void inativa(@PathVariable("id") @NotNull Long id) {
        facade.deleteAluno(id);
    }

}
