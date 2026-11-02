package tccapi.model;

import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import tccapi.model.Enum.PreferenciaTema;
import tccapi.model.Enum.TipoPerfil;

@Entity
@Table(name = "orientadores")
@Getter @Setter 
public class Orientador extends Usuario {

    @ElementCollection
    @CollectionTable(name = "orientador_areas_atuacao", joinColumns = @JoinColumn(name = "orientador_id"))
    @Column(name = "area")
    private List<String> areasAtuacao = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private PreferenciaTema perfilAceito;

    private String descricao;

    private Boolean aceitaOrientados = true;

    @Column(name = "contato" )
    private String contato;

    @PrePersist
    private void definirPerfil() {
        setPerfil(TipoPerfil.ORIENTADOR);
    }
}
