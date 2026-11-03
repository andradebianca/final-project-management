package tccapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import tccapi.service.UsuarioDetailsService;

@Configuration
@EnableWebSecurity // Ativa o Spring Security. Ou seja, todas as requisições passam pela camada de segurança. 
/* Request > Security > Controller > Service > Banco   */
@RequiredArgsConstructor //Lombok: gera o construtor vazio.
public class SecurityConfig {

    private final SecurityFilter securityFilter;

    @Bean //qualquer objeto Java que é instanciado, montado, configurado e gerenciado por um Spring IoC Container
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); //Método para criar criptografia de senhas para o banco.
    }

    @Bean
    public AuthenticationProvider authenticationProvider( //Quem vai validar as credenciais do usuario.
            UsuarioDetailsService usuarioDetailsService,
            PasswordEncoder passwordEncoder) {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(usuarioDetailsService);

        provider.setPasswordEncoder(passwordEncoder);

        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager( //recebe e coordena a autenticacao.
            AuthenticationProvider authenticationProvider) {

        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            AuthenticationProvider authenticationProvider)
            throws Exception {

        http
            .csrf(csrf -> csrf.disable()) //Vamos trabalhar com Token e não com sessão.
            .sessionManagement(s ->
                s.sessionCreationPolicy(SessionCreationPolicy.STATELESS) 
            )
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs", "/v3/api-docs/**").permitAll() //Qualquer pessoa pode abrir o swagger.
                .requestMatchers(HttpMethod.POST, "/auth/login", "/alunos/**", "/universidades/**", "/cursos/**", "/orientador/**").permitAll()
                .anyRequest().authenticated() //Todo o resto precisa ser autenticado.
            )
            .exceptionHandling(ex -> ex //personalização dos erros.
            .authenticationEntryPoint((req, res, e) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED)) 
            .accessDeniedHandler((req, res, e) -> res.sendError(HttpServletResponse.SC_FORBIDDEN))
            )
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(
                securityFilter,
                UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }
}