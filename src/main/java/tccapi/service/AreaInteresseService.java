package tccapi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tccapi.model.AreaInteresse;
import tccapi.repository.AreaInteresseRepository;

import java.util.List;

@Service
public class AreaInteresseService {

    private final AreaInteresseRepository areaInteresseRepository;

    public AreaInteresseService(
            AreaInteresseRepository areaInteresseRepository) {
        this.areaInteresseRepository = areaInteresseRepository;
    }

    public List<AreaInteresse> listarTodas() {
        return areaInteresseRepository.findAll();
    }

    public AreaInteresse buscarPorId(Long id) {
        return areaInteresseRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Área de interesse não encontrada: " + id));
    }

    @Transactional
    public AreaInteresse cadastrar(AreaInteresse area) {
        return areaInteresseRepository.save(area);
    }

    @Transactional
    public AreaInteresse atualizar(
            Long id, AreaInteresse dados) {

        AreaInteresse area = buscarPorId(id);

        area.setNome(dados.getNome());
        area.setDescricao(dados.getDescricao());

        return areaInteresseRepository.save(area);
    }

    @Transactional
    public void excluir(Long id) {
        AreaInteresse area = buscarPorId(id);
        areaInteresseRepository.delete(area);
    }
}