package tccapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import tccapi.dto.LoginRequest;
import tccapi.dto.LoginResponse;
import tccapi.service.AutenticacaoService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AutenticacaoController {

    private final AutenticacaoService autenticacaoService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest dados) {

        return ResponseEntity.ok(
            autenticacaoService.autenticar(dados)
        );
    }
}