package tccapi.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tccapi.dto.AlunoRequest;
import tccapi.dto.AlunoResponse;
import tccapi.dto.IdeiaRequest;
import tccapi.service.AlunoService;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    private final AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlunoResponse cadastrar(@RequestBody @Valid AlunoRequest req) {
        return service.cadastrar(req);
    }

    @PutMapping("/{id}/ideia")
    public AlunoResponse registrarIdeia(@PathVariable Long id,
                                        @RequestBody @Valid IdeiaRequest req) {
        return service.registrarIdeia(id, req);
    }

    @GetMapping("/{id}")
    public AlunoResponse buscar(@PathVariable Long id) {
        return service.buscar(id);
    }
}