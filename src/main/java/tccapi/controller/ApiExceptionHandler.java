package tccapi.controller;

import tccapi.exception.UsuarioNaoCadastradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(UsuarioNaoCadastradoException.class)
    public ResponseEntity<Map<String, String>> usuarioNaoCadastrado(UsuarioNaoCadastradoException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(Map.of("erro", e.getMessage()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, String>> senhaIncorreta(BadCredentialsException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(Map.of("erro", "Senha incorreta"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> dadosInvalidos(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest()
            .body(Map.of("erro", "Login e senha são obrigatórios"));
    }
}