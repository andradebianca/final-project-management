package tccapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "aluno")
public class Aluno extends Usuario {

    @Column(unique = true, nullable = false)
    private String matricula;

    @Column(nullable = false)
    private String curso;

    @Column(name = "email_institucional", unique = true, nullable = false)
    private String emailInstitucional;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_cadastro", nullable = false)
    private StatusCadastro statusCadastro = StatusCadastro.PENDENTE_IDEIA;

    @OneToOne(mappedBy = "aluno", cascade = CascadeType.ALL)
    private IdeiaTcc ideia;

    protected Aluno() {}

    public Aluno(String nome, String matricula, String curso,
                 String emailInstitucional, String senhaCriptografada) {
        super(nome, matricula, senhaCriptografada, Perfil.ALUNO); // login = matrícula
        this.matricula = matricula;
        this.curso = curso;
        this.emailInstitucional = emailInstitucional;
    }

    public void registrarIdeia(IdeiaTcc ideia) {
        this.ideia = ideia;
        this.statusCadastro = StatusCadastro.COMPLETO;
    }

    public String getMatricula() { return matricula; }
    public String getCurso() { return curso; }
    public String getEmailInstitucional() { return emailInstitucional; }
    public StatusCadastro getStatusCadastro() { return statusCadastro; }
    public IdeiaTcc getIdeia() { return ideia; }
}