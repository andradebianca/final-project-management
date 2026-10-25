package tccapi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tccapi.model.Orientador;
import tccapi.repository.OrientadorRepository;

import java.util.List;

@Service
public class OrientadorService {

    private final OrientadorRepository orientadorRepository;

    public OrientadorService(
            OrientadorRepository orientadorRepository) {
        this.orientadorRepository = orientadorRepository;
    }

    public List<Orientador> listarTodos() {
        return orientadorRepository.findAll();
    }

    public Orientador buscarPorId(Long id) {
        return orientadorRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Orientador não encontrado: " + id));
    }

    @Transactional
    public Orientador cadastrar(Orientador orientador) {
        return orientadorRepository.save(orientador);
    }

    @Transactional
    public void excluir(Long id) {
        Orientador orientador = buscarPorId(id);
        orientadorRepository.delete(orientador);
    }
}