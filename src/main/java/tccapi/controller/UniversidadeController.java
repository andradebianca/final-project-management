package tccapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import tccapi.dto.UniversidadeRequest;
import tccapi.dto.UniversidadeResponse;
import tccapi.service.UniversidadeService;

@RestController
@RequestMapping("/universidades")
@RequiredArgsConstructor
public class UniversidadeController {

    private final UniversidadeService universidadeService;

    @PostMapping
    public ResponseEntity<UniversidadeResponse> criar(
            @RequestBody @Validated UniversidadeRequest request) {

        UniversidadeResponse response =
                universidadeService.criar(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public List<UniversidadeResponse> listar() {
        return universidadeService.listar();
    }
}