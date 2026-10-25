package tccapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tccapi.model.Orientador;
import tccapi.service.OrientadorService;

import java.util.List;

@RestController
@RequestMapping("/orientadores")
public class OrientadorController {

    private final OrientadorService orientadorService;

    public OrientadorController(
            OrientadorService orientadorService) {
        this.orientadorService = orientadorService;
    }

    @GetMapping
    public ResponseEntity<List<Orientador>> listarTodos() {
        return ResponseEntity.ok(
                orientadorService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orientador> buscarPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(
                orientadorService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Orientador> cadastrar(
            @RequestBody Orientador orientador) {
        Orientador novoOrientador =
                orientadorService.cadastrar(orientador);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(novoOrientador);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {
        orientadorService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}