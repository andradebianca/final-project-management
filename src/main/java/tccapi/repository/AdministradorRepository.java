package tccapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tccapi.model.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
}