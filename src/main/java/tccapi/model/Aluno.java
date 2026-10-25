package tccapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import tccapi.model.Enum.Perfil;
import tccapi.model.Enum.Universidade;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "aluno")
public class Aluno extends Usuario {

    @NotBlank
    @Column(unique = true, nullable = false)
    private String matricula;

    @NotBlank
    @Column(nullable = false)
    private String curso;

    @NotBlank
    @Email
    @Column(name = "email_institucional", unique = true, nullable = false)
    private String emailInstitucional;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Universidade universidade;

    // Relação ManyToMany ajustada para corresponder ao Tcc.java
    @ManyToMany(mappedBy = "alunos")
    private Set<Tcc> tccs = new LinkedHashSet<>();

    protected Aluno() {}

    public Aluno(String nomeCompleto, String matricula, String curso,
                 String emailInstitucional, String senha,
                 Universidade universidade) {
        super(nomeCompleto, emailInstitucional, senha, Perfil.ALUNO);
        this.matricula = matricula;
        this.curso = curso;
        this.universidade = universidade;
    }

    // --- Getters e Setters ---

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getEmailInstitucional() {
        return emailInstitucional;
    }

    public void setEmailInstitucional(String emailInstitucional) {
        this.emailInstitucional = emailInstitucional;
    }

    public Universidade getUniversidade() {
        return universidade;
    }

    public void setUniversidade(Universidade universidade) {
        this.universidade = universidade;
    }

    public Set<Tcc> getTccs() {
        return tccs;
    }

    public void setTccs(Set<Tcc> tccs) {
        this.tccs = tccs;
    }

    /**
     * Método auxiliar para manter a compatibilidade com o AlunoResponse
     */
    public Tcc getTcc() {
        return tccs.isEmpty() ? null : tccs.iterator().next();
    }
}