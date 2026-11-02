package tccapi.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import tccapi.model.Cursos;

public interface CursosRepository extends JpaRepository<Cursos, Long> {

    Optional<Cursos> findByNome(String nome);

    boolean existsByNome(String nome);
}