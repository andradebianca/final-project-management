package tccapi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tccapi.model.Aluno;
import tccapi.repository.AlunoRepository;

import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    public Aluno buscarPorId(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Aluno não encontrado: " + id));
    }

    @Transactional
    public Aluno cadastrar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    @Transactional
    public Aluno atualizar(Long id, Aluno dados) {
        Aluno aluno = buscarPorId(id);
        
        aluno.setMatricula(dados.getLogin());
        aluno.setCurso(dados.getCurso());
        aluno.setEmailInstitucional(dados.getEmailInstitucional());
        aluno.setUniversidade(dados.getUniversidade());

        return alunoRepository.save(aluno);
    }

    @Transactional
    public void excluir(Long id) {
        Aluno aluno = buscarPorId(id);
        alunoRepository.delete(aluno);
    }
}