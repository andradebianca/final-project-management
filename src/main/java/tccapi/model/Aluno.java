package tccapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tccapi.model.Enum.TipoPerfil;

@Entity
@Table(name = "alunos")
@Getter @Setter 
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Aluno extends Usuario {

    @Column(nullable = false, unique = true)
    private Integer matricula;

    @ManyToOne
    @JoinColumn(name = "cursos_id")
    private Cursos curso;

    @Column(name = "contato" )
    private String contato;

    @PrePersist
    private void definirPerfil() {
        setPerfil(TipoPerfil.ALUNO);
    }

    public Aluno(String senha, String nome, String email, Universidade universidade, String img, Integer matricula, Cursos curso, String contato)
    {
        super(senha, nome, email, universidade, img);
        this.matricula = matricula;
        this.curso = curso;
        this.contato = contato;
    }
}

    
