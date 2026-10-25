package tccapi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tccapi.model.Tcc;
import tccapi.repository.TccRepository;

import java.util.List;

@Service
public class TccService {

    private final TccRepository tccRepository;

    public TccService(TccRepository tccRepository) {
        this.tccRepository = tccRepository;
    }

    public List<Tcc> listarTodos() {
        return tccRepository.findAll();
    }

    public Tcc buscarPorId(Long id) {
        return tccRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "TCC não encontrado: " + id));
    }

    @Transactional
    public Tcc cadastrar(Tcc tcc) {
        return tccRepository.save(tcc);
    }

    @Transactional
    public void excluir(Long id) {
        Tcc tcc = buscarPorId(id);
        tccRepository.delete(tcc);
    }
}