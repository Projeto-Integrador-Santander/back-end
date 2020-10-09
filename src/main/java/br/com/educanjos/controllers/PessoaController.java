package br.com.educanjos.controllers;

import java.util.List;

import br.com.educanjos.facades.PerfilFacade;
import br.com.educanjos.models.dto.PessoaEntrada;
import br.com.educanjos.models.entities.Perfil;
import br.com.educanjos.models.enums.TipoCadastroPessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.sun.istack.NotNull;
import br.com.educanjos.facades.PessoaFacade;
import br.com.educanjos.models.entities.Pessoa;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("Pessoa/v1")
@Validated
@AllArgsConstructor
public class PessoaController {
	@Autowired
    private PessoaFacade facade;
    @Autowired
    private PerfilFacade perfilFacade;

    @PostMapping("{tipo}")
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoa newPessoa(@PathVariable("tipo") String tipo,
                            @RequestParam("id_perfil") Long idPerfil,
                            @RequestParam("id_login") Long idLogin,
                            @RequestParam("id_endereco") Long idEndereco) {
        PessoaEntrada Pessoa = new PessoaEntrada(tipo, idLogin, idEndereco, idPerfil);
        return facade.newPessoa(Pessoa);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Pessoa getById(@PathVariable("id") @NotNull Long id) {
        return facade.getPessoaById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Pessoa> getAll() {
        return facade.getAllPessoa();
    }

    @PatchMapping("/inativa/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void inativa(@PathVariable("id") @NotNull Long id) {
        perfilFacade.deletePerfil(id);
        facade.deletePessoa(id);
    }

}
