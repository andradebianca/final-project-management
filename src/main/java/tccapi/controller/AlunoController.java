package tccapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tccapi.dto.AlunoResponse;
import tccapi.model.Aluno;
import tccapi.service.AlunoService;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponse>> listarTodos() {
        // Converte cada Aluno da lista para AlunoResponse
        List<AlunoResponse> alunosResponse = alunoService.listarTodos().stream()
                .map(AlunoResponse::de)
                .toList();
        
        return ResponseEntity.ok(alunosResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponse> buscarPorId(@PathVariable Long id) {
        Aluno aluno = alunoService.buscarPorId(id);
        return ResponseEntity.ok(AlunoResponse.de(aluno));
    }

    @PostMapping
    public ResponseEntity<AlunoResponse> cadastrar(@RequestBody Aluno aluno) {
        Aluno novoAluno = alunoService.cadastrar(aluno);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(AlunoResponse.de(novoAluno));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponse> atualizar(
            @PathVariable Long id,
            @RequestBody Aluno aluno) {
        Aluno alunoAtualizado = alunoService.atualizar(id, aluno);
        return ResponseEntity.ok(AlunoResponse.de(alunoAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        alunoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}