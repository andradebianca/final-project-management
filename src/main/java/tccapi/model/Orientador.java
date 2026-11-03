package tccapi.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tccapi.model.Enum.PreferenciaTema;
import tccapi.model.Enum.TipoPerfil;

@Entity
@Table(name = "orientadores")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Orientador extends Usuario {

    @ElementCollection
    @CollectionTable(name = "orientador_areas_atuacao", joinColumns = @JoinColumn(name = "orientador_id"))
    @Column(name = "area")
    private List<String> areasAtuacao = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private PreferenciaTema perfilAceito;

    private String descricao;

    private Boolean aceitaOrientados = true;

    private String contato;

    public Orientador(List<String> areasAtuacao,
                      PreferenciaTema perfilAceito,
                      String descricao,
                      Boolean aceitaOrientados,
                      String contato) {
        this.areasAtuacao = areasAtuacao;
        this.perfilAceito = perfilAceito;
        this.descricao = descricao;
        this.aceitaOrientados = aceitaOrientados;
        this.contato = contato;
    }

    @PrePersist
    private void definirPerfil() {
        setPerfil(TipoPerfil.ORIENTADOR);
    }
}