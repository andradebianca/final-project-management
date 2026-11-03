package tccapi.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import tccapi.dto.OrientadorRequest;
import tccapi.dto.OrientadorResponse;
import tccapi.service.OrientadorService;

@RestController
@RequestMapping("/orientador")
public class OrientadorController {

    private final OrientadorService orientadorService;

    public OrientadorController(OrientadorService orientadorService) {
        this.orientadorService = orientadorService;
    }

    @PostMapping
    public ResponseEntity<OrientadorResponse> criar(@Valid @RequestBody OrientadorRequest req) {
        OrientadorResponse criado = orientadorService.criar(req);
        return ResponseEntity.created(URI.create("/orientador/" + criado.id())).body(criado);
    }

    @GetMapping
    public List<OrientadorResponse> listar() {
        return orientadorService.listar();
    }

    @GetMapping("/{id}")
    public OrientadorResponse buscar(@PathVariable Long id) {
        return orientadorService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public OrientadorResponse atualizar(@PathVariable Long id,
                                        @Valid @RequestBody OrientadorRequest req) {
        return orientadorService.atualizar(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@PathVariable Long id) {
        orientadorService.inativar(id);
    }
}