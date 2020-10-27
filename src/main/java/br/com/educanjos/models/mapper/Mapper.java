package br.com.educanjos.models.mapper;

import br.com.educanjos.models.dto.PessoaEntrada;
import br.com.educanjos.models.entities.*;
import br.com.educanjos.models.enums.TipoCadastroPessoa;
import com.sun.istack.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {

    public static Pessoa entradaToEntity(@NotNull PessoaEntrada entrada) {
        Pessoa pessoa = new Pessoa();
        pessoa.setLogin(new Login(entrada.getLogin()));
        pessoa.setPerfil(new Perfil(entrada.getPerfil()));
        pessoa.setTipoCadastro(TipoCadastroPessoa.get(entrada.getTipoCadastro()));
        List<Materia> materias = entrada.getMaterias().stream().map(id -> new Materia(id)).collect(Collectors.toList());
        pessoa.setMateria(materias);
        return pessoa;
    }

    public static ProfessorMateria entradaToEntity(@NotNull Long idProfessor, @NotNull Long idMateria) {
        ProfessorMateria pessoa = new ProfessorMateria();
        pessoa.setMateria(new Materia(idMateria));
        pessoa.setProfessor(new Pessoa(idProfessor));
        return pessoa;
    }
}
