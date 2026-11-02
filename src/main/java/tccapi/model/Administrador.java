package tccapi.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tccapi.model.Enum.TipoPerfil;

@Entity
@Table(name = "administradores")
@Setter @Getter 
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Administrador extends Usuario {
    
    @PrePersist
    private void definirPerfil() {
        setPerfil(TipoPerfil.ADMINISTRADOR);
    }
}
