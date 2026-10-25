package tccapi.model;

import jakarta.persistence.*;
import tccapi.model.Enum.ModalidadeOrientacao;
import tccapi.model.Enum.Perfil;
import tccapi.model.Enum.Universidade;

@Entity
@Table(name = "orientador")
@PrimaryKeyJoinColumn(name = "usuario_id")
public class Orientador extends Usuario {

    @Enumerated(EnumType.STRING)
    @Column(name = "id_universidade", nullable = false)
    private Universidade idUniversidade;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_interesse", nullable = false)
    private AreaInteresse areaInteresse;

    @Enumerated(EnumType.STRING)
    @Column(name = "modalidade_orientacao", nullable = false)
    private ModalidadeOrientacao modalidadeOrientacao;

    public Orientador() {
    }

    public Orientador(
            String nomeCompleto,
            String emailInstitucional,
            String senha,
            Universidade idUniversidade,
            AreaInteresse areaInteresse,
            ModalidadeOrientacao modalidadeOrientacao
    ) {
        super(nomeCompleto, emailInstitucional, senha, Perfil.ORIENTADOR);

        this.idUniversidade = idUniversidade;
        this.areaInteresse = areaInteresse;
        this.modalidadeOrientacao = modalidadeOrientacao;
    }

    public Universidade getIdUniversidade() {
        return idUniversidade;
    }

    public void setIdUniversidade(Universidade idUniversidade) {
        this.idUniversidade = idUniversidade;
    }

    public AreaInteresse getAreaInteresse() {
        return areaInteresse;
    }

    public void setAreaInteresse(AreaInteresse areaInteresse) {
        this.areaInteresse = areaInteresse;
    }

    public ModalidadeOrientacao getModalidadeOrientacao() {
        return modalidadeOrientacao;
    }

    public void setModalidadeOrientacao(ModalidadeOrientacao modalidadeOrientacao) {
        this.modalidadeOrientacao = modalidadeOrientacao;
    }
}