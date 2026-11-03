package tccapi.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tccapi.dto.LoginRequest;
import tccapi.dto.LoginResponse;
import tccapi.model.Usuario;

@Service
@RequiredArgsConstructor
public class AutenticacaoService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public LoginResponse autenticar(LoginRequest dados) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dados.getEmail(),
                        dados.getSenha()
                )
        );

        Usuario usuario = (Usuario) authentication.getPrincipal();

        String token = tokenService.gerarToken(usuario);

        return new LoginResponse(
                token,
                "Bearer",
                usuario.getId(),
                usuario.getNome(),
                usuario.getPerfil(),
                usuario.getImg()
        );
    }
}