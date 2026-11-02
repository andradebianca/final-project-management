package tccapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import tccapi.dto.CursosRequest;
import tccapi.dto.CursosResponse;
import tccapi.service.CursosService;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursosController {

    private final CursosService cursosService;

    @PostMapping
    public ResponseEntity<CursosResponse> criar(
            @RequestBody @Validated CursosRequest request) {

        CursosResponse response =
                cursosService.criar(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<CursosResponse>> listar() {

        return ResponseEntity.ok(
                cursosService.listar()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursosResponse> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                cursosService.buscarPorId(id)
        );
    }
}