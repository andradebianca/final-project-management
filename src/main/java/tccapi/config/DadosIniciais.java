package tccapi.config;

import tccapi.model.Administrador;
import tccapi.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DadosIniciais {

    @Bean
    CommandLineRunner criarUsuarios(UsuarioRepository repo, PasswordEncoder encoder) {
        return args -> {
            if (repo.count() == 0) {
                repo.save(new Administrador("Administrador", "admin", encoder.encode("admin")));

            }
        };
    }
}