package tccapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tccapi.dto.UniversidadeRequest;
import tccapi.dto.UniversidadeResponse;
import tccapi.model.Universidade;
import tccapi.repository.UniversidadeRepository;

@Service
@RequiredArgsConstructor
public class UniversidadeService {

    private final UniversidadeRepository universidadeRepository;

    public UniversidadeResponse criar(UniversidadeRequest request) {

    Universidade universidade = new Universidade(request.nome());

    universidade = universidadeRepository.save(universidade);

    return new UniversidadeResponse(
            universidade.getId(),
            universidade.getNome()
    );
}

    public List<UniversidadeResponse> listar() {
        return universidadeRepository.findAll()
                .stream()
                .map(u -> new UniversidadeResponse(
                        u.getId(),
                        u.getNome()))
                .toList();
    }
}