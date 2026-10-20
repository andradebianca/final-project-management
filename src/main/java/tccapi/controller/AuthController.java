package tccapi.controller;

import tccapi.dto.LoginRequest;
import tccapi.dto.LoginResponse;
import tccapi.exception.UsuarioNaoCadastradoException;
import tccapi.model.Usuario;
import tccapi.repository.UsuarioRepository;
import tccapi.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest dto) {

        usuarioRepository.findByLogin(dto.login())
            .orElseThrow(UsuarioNaoCadastradoException::new);

        var authToken = new UsernamePasswordAuthenticationToken(dto.login(), dto.senha());
        var auth = authenticationManager.authenticate(authToken);

        var usuario = (Usuario) auth.getPrincipal();
        var token = tokenService.gerarToken(usuario);

        return ResponseEntity.ok(
            new LoginResponse(token, usuario.getNome(), "Login realizado com sucesso"));
    }
}