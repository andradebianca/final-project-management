package tccapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tccapi.model.Orientador;
import tccapi.model.Enum.PreferenciaTema;

public interface OrientadorRepository extends JpaRepository<Orientador, Long> {

    List<Orientador> findByAceitaOrientadosTrue();

    List<Orientador> findByPerfilAceito(PreferenciaTema perfilAceito);

    @Query("SELECT o FROM Orientador o JOIN o.areasAtuacao a WHERE a = :area")
    List<Orientador> buscarPorArea(@Param("area") String area);
}