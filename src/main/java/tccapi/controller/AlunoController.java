package tccapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import tccapi.dto.AlunoRequest;
import tccapi.dto.AlunoResponse;
import tccapi.service.AlunoService;

@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    // Cadastrar aluno
    @PostMapping
    public ResponseEntity<AlunoResponse> cadastrar(
            @Valid @RequestBody AlunoRequest request) {

        AlunoResponse aluno = alunoService.cadastrar(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(aluno);
    }

    // Listar todos os alunos
    @GetMapping
    public ResponseEntity<List<AlunoResponse>> listarTodos() {

        return ResponseEntity.ok(alunoService.listarTodos());
    }
}