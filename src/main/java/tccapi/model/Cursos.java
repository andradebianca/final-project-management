package tccapi.model;

import jakarta.persistence.*;

@Entity 
@Table (name = "cursos")
public class Cursos {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;
    
}
