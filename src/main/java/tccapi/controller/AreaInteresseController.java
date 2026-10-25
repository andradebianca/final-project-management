package tccapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tccapi.model.AreaInteresse;
import tccapi.service.AreaInteresseService;

import java.util.List;

@RestController
@RequestMapping("/areas-interesse")
public class AreaInteresseController {

    private final AreaInteresseService areaInteresseService;

    public AreaInteresseController(
            AreaInteresseService areaInteresseService) {
        this.areaInteresseService = areaInteresseService;
    }

    @GetMapping
    public ResponseEntity<List<AreaInteresse>> listarTodas() {
        return ResponseEntity.ok(
                areaInteresseService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AreaInteresse> buscarPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(
                areaInteresseService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<AreaInteresse> cadastrar(
            @RequestBody AreaInteresse area) {
        AreaInteresse novaArea =
                areaInteresseService.cadastrar(area);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(novaArea);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AreaInteresse> atualizar(
            @PathVariable Long id,
            @RequestBody AreaInteresse area) {
        return ResponseEntity.ok(
                areaInteresseService.atualizar(id, area));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {
        areaInteresseService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}