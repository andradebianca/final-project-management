package tccapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tccapi.dto.CursosRequest;
import tccapi.dto.CursosResponse;
import tccapi.model.Cursos;
import tccapi.repository.CursosRepository;

@Service
@RequiredArgsConstructor
public class CursosService {

    private final CursosRepository cursosRepository;

    public CursosResponse criar(CursosRequest request) {

        Cursos curso = new Cursos(request.nome());

        curso = cursosRepository.save(curso);

        return new CursosResponse(
                curso.getId(),
                curso.getNome()
        );
    }

    public List<CursosResponse> listar() {

        return cursosRepository.findAll()
                .stream()
                .map(curso -> new CursosResponse(
                        curso.getId(),
                        curso.getNome()))
                .toList();
    }

    public CursosResponse buscarPorId(Long id) {

        Cursos curso = cursosRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Curso não encontrado"));

        return new CursosResponse(
                curso.getId(),
                curso.getNome()
        );
    }
}