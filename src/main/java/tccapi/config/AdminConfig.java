package tccapi.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import tccapi.model.Usuario;
import tccapi.model.Enum.TipoPerfil;
import tccapi.repository.UsuarioRepository;

@Configuration
public class AdminConfig {

    @Bean
    CommandLineRunner criarAdministrador(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            if (!usuarioRepository.existsByEmail("admin")) {

                Usuario admin = new Usuario(
                    passwordEncoder.encode("admin"),
                    "Administrador",
                    "admin",
                    null,
                    null
                );

                admin.setPerfil(TipoPerfil.ADMINISTRADOR);

                usuarioRepository.save(admin);

                System.out.println("Administrador criado com sucesso!");

            } else {
                System.out.println("Administrador já cadastrado.");
            }
        };
    }
}