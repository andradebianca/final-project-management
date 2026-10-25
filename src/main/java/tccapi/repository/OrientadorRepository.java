package tccapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tccapi.model.Orientador;

public interface OrientadorRepository
        extends JpaRepository<Orientador, Long> {
}
