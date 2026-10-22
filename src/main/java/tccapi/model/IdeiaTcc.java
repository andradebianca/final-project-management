package tccapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ideia_tcc")
public class IdeiaTcc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "aluno_id", unique = true)
    private Aluno aluno;

    @Column(name = "sem_ideia", nullable = false)
    private boolean semIdeia;

    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    protected IdeiaTcc() {}

    public IdeiaTcc(Aluno aluno, boolean semIdeia, String titulo, String descricao) {
        this.aluno = aluno;
        this.semIdeia = semIdeia;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Long getId() { return id; }
    public boolean isSemIdeia() { return semIdeia; }
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
}