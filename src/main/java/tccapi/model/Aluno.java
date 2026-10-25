package tccapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import tccapi.model.Enum.Perfil;
import tccapi.model.Enum.Universidade;

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
    @ManyToOne(optional = false)
    @JoinColumn(name = "universidade_id", nullable = false)
    private Universidade universidade;

    @JsonIgnoreProperties("aluno")
    @OneToOne(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    private IdeiaTcc ideia;

    protected Aluno() {}

    public Aluno(String nomeCompleto, String matricula, String curso,
                 String emailInstitucional, String senha,
                 Universidade universidade) {
        super(nomeCompleto, emailInstitucional, senha, Perfil.ALUNO);
        this.matricula = matricula;
        this.curso = curso;
        this.universidade = universidade;
    }


}