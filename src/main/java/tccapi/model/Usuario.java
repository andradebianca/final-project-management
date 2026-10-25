package tccapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import tccapi.model.Enum.Perfil;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.JOINED)
// Esconde da API tudo o que vem do UserDetails (inclusive o hash da senha)
@JsonIgnoreProperties({"password", "username", "authorities",
        "accountNonExpired", "accountNonLocked", "credentialsNonExpired", "enabled"})
public abstract class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private String login;

    // Só entra na API (cadastro); nunca sai nas respostas
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 8)
    @Column(nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Perfil perfil;

    protected Usuario() {}

    protected Usuario(String nome, String login, String senha, Perfil perfil) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.perfil = perfil;
    }

    // ---------- getters ----------
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getLogin() { return login; }
    public Perfil getPerfil() { return perfil; }

    // ---------- alterações controladas (usadas pelas subclasses) ----------
    protected void alterarNome(String nome) { this.nome = nome; }
    protected void alterarLogin(String login) { this.login = login; }

    // ---------- métodos do UserDetails ----------
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + perfil.name()));
    }

    @Override
    public String getPassword() { return senha; }

    @Override
    public String getUsername() { return login; }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}