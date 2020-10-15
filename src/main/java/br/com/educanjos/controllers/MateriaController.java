package br.com.educanjos.controllers;

import br.com.educanjos.facades.MateriaFacade;
import br.com.educanjos.models.entities.Materia;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("materia/v1")
@Validated
@AllArgsConstructor
public class MateriaController {

    @Autowired
    private MateriaFacade facade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Materia newMateria(@RequestBody Materia Materia) {
        return facade.newMateria(Materia);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Materia getById(@PathVariable("id") @NotNull Long id) {
        return facade.getMateriaById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Materia> getAll() {
        return facade.getAllMateria();
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") @NotNull Long id) {
        facade.deleteMateria(id);
    }

}
