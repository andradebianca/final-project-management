package tccapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tccapi.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    boolean existsByEmail(String email);

    boolean existsByMatricula(Integer matricula);
}