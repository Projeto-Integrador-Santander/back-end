package br.com.educanjos.controllers;

import br.com.educanjos.facades.ProfessorMateriaFacade;
import br.com.educanjos.models.entities.ProfessorMateria;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("professor-materia/v1")
@Validated
@AllArgsConstructor
public class ProfessorMateriaController {

	@Autowired
    private ProfessorMateriaFacade facade;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ProfessorMateria newProfessorMateria(@RequestBody ProfessorMateria professorMateria) {
        return facade.newProfessorMateria(professorMateria);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProfessorMateria getById(@PathVariable("id") @NotNull Long id) {
        return facade.getProfessorMateriaById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProfessorMateria> getAll() {
        return facade.getAllProfessorMateria();
    }

    @PatchMapping("/inativa/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void inativa(@PathVariable("id") @NotNull Long id) {
        facade.deleteProfessorMateria(id);
    }

}
