package tccapi.service;

import tccapi.exception.UsuarioNaoCadastradoException;
import tccapi.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    private final UsuarioRepository repository;

    public AuthService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) {
        return repository.findByLogin(login)
            .orElseThrow(UsuarioNaoCadastradoException::new);
    }
}