package tccapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;

@Entity 
@Table (name = "cursos")
@Setter @Getter 
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cursos {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter (AccessLevel.NONE)
    private Long id;

    @Column(nullable = false)
    private String nome;
    
    public Cursos (String nome){
        this.nome = nome;
    }
}
