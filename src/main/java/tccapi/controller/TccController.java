package tccapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tccapi.model.Tcc;
import tccapi.service.TccService;

import java.util.List;

@RestController
@RequestMapping("/tccs")
public class TccController {

    private final TccService tccService;

    public TccController(TccService tccService) {
        this.tccService = tccService;
    }

    @GetMapping
    public ResponseEntity<List<Tcc>> listarTodos() {
        return ResponseEntity.ok(tccService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tcc> buscarPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(tccService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Tcc> cadastrar(
            @RequestBody Tcc tcc) {
        Tcc novoTcc = tccService.cadastrar(tcc);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(novoTcc);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {
        tccService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}