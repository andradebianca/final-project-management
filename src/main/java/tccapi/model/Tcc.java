package tccapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import tccapi.model.Enum.Etapa;
import tccapi.model.Enum.Grupo;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tcc")
public class Tcc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    // null = "sem orientador" (padrão)
    @ManyToOne
    @JoinColumn(name = "orientador_id")
    private Orientador orientador;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Grupo grupo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Etapa etapa;

    // 1 aluno (INDIVIDUAL) ou 2 alunos (DUPLA)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tcc_aluno",
            joinColumns = @JoinColumn(name = "tcc_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id"))
    private Set<Aluno> alunos = new LinkedHashSet<>();

    // No TCC 2, aponta para o TCC 1 que ele continua (unique: um TCC 1 tem no máximo um TCC 2)
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "tcc_anterior_id", unique = true)
    private Tcc tccAnterior;

    protected Tcc() {}

    // ---------- criação ----------

    public static Tcc criarTcc1(String nome, String descricao, Grupo grupo,
                                Set<Aluno> alunos, Orientador orientador) {
        Tcc tcc = new Tcc();
        tcc.nome = nome;
        tcc.descricao = descricao;
        tcc.grupo = grupo;
        tcc.etapa = Etapa.TCC_1;
        tcc.alunos = new LinkedHashSet<>(alunos);
        tcc.orientador = orientador;
        return tcc;
    }

    // Continuação do TCC 1: mesmos alunos e mesmo grupo, orientador próprio.
    // Se nome/descrição não vierem, aproveita os do TCC 1.
    public static Tcc criarTcc2(Tcc tcc1, String nome, String descricao, Orientador orientador) {
        Tcc tcc = new Tcc();
        tcc.nome = (nome == null || nome.isBlank()) ? tcc1.nome : nome;
        tcc.descricao = (descricao == null || descricao.isBlank()) ? tcc1.descricao : descricao;
        tcc.grupo = tcc1.grupo;
        tcc.etapa = Etapa.TCC_2;
        tcc.alunos = new LinkedHashSet<>(tcc1.alunos);
        tcc.orientador = orientador;
        tcc.tccAnterior = tcc1;
        return tcc;
    }

    public void atualizar(String nome, String descricao, Orientador orientador) {
        this.nome = nome;
        this.descricao = descricao;
        this.orientador = orientador;
    }

    // ---------- getters ----------
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public Orientador getOrientador() { return orientador; }
    public Grupo getGrupo() { return grupo; }
    public Etapa getEtapa() { return etapa; }
    public Set<Aluno> getAlunos() { return alunos; }

    @JsonProperty(value = "tccAnteriorId", access = JsonProperty.Access.READ_ONLY)
    public Long getTccAnteriorId() {
        return tccAnterior == null ? null : tccAnterior.getId();
    }
}