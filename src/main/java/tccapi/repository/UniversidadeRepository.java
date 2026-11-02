package tccapi.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import tccapi.model.Universidade;

public interface UniversidadeRepository extends JpaRepository<Universidade, Long> {

    Optional<Universidade> findByNome(String nome);

    boolean existsByNome(String nome);
}