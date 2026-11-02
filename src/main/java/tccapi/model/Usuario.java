package tccapi.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AccessLevel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tccapi.model.Enum.StatusUser;
import tccapi.model.Enum.TipoPerfil;

@Entity 
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter 
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Usuario implements UserDetails{

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter (AccessLevel.NONE)
    private Long id;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPerfil perfil;

    @ManyToOne 
    @JoinColumn(name = "universidade_id")
    private Universidade universidade;

    @Column(name = "imagem")
    private String img;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusUser status = StatusUser.ATIVO;

    @Column(name = "criado_em", updatable = false)
    private LocalDateTime criadoEm;
    
    @PrePersist //será executado esse método antes de salvar uma nova entidade no banco.
    protected void aoCriar(){
        this.criadoEm = LocalDateTime.now();
    }

    public Usuario(String senha, String nome, String email, Universidade universidade, String img)
    {
        this.senha = senha;
        this.nome = nome;
        this.email = email;
        this.universidade = universidade;
        this.img = img;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // ajuste conforme seu Enum de perfil, ex: ROLE_ALUNO, ROLE_ORIENTADOR...
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.getClass().getSimpleName().toUpperCase()));
    }

    @Override public String getPassword() { return senha; }
    @Override public String getUsername() { return email; }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
