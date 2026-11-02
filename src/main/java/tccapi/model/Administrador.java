package tccapi.model;

import jakarta.persistence.*;
import tccapi.model.Enum.TipoPerfil;

@Entity
@Table(name = "administradores")
public class Administrador extends Usuario {

    @PrePersist
    private void definirPerfil() {
        setPerfil(TipoPerfil.ADMINISTRADOR);
    }
}
