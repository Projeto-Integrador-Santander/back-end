package br.com.educanjos.repositories;

import br.com.educanjos.models.entities.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Perfil AS p SET p.status = '1' WHERE p.id = ?1")
    void updateInactivePerfil(Long id);

    Perfil findByCpf(String cpf);
}
