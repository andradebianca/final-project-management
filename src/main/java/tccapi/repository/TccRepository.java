package tccapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tccapi.model.Tcc;

public interface TccRepository
        extends JpaRepository<Tcc, Long> {
}
