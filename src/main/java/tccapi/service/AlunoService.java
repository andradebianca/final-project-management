package tccapi.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;
import tccapi.model.Aluno;
import tccapi.model.Universidade;
import tccapi.model.Cursos;
import tccapi.dto.AlunoRequest;
import tccapi.dto.AlunoResponse;
import tccapi.repository.AlunoRepository;
import tccapi.repository.UniversidadeRepository;
import tccapi.repository.CursosRepository;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final UniversidadeRepository universidadeRepository;
    private final CursosRepository cursosRepository;
    private final PasswordEncoder passwordEncoder;

    // Cadastrar aluno
    @Transactional
    public AlunoResponse cadastrar(AlunoRequest request) {

        // Verificar se o e-mail já está cadastrado
        if (alunoRepository.existsByEmail(request.email())) {
            throw new RuntimeException("E-mail já cadastrado.");
        }

        // Verificar se a matrícula já está cadastrada
        if (alunoRepository.existsByMatricula(request.matricula())) {
            throw new RuntimeException("Matrícula já cadastrada.");
        }

        // Buscar universidade
        Universidade universidade = universidadeRepository
                .findById(request.universidadeId())
                .orElseThrow(() -> new RuntimeException("Universidade não encontrada."));

        // Buscar curso
        Cursos curso = cursosRepository
                .findById(request.cursoId())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado."));

        // Criptografar senha
        String senhaCodificada = passwordEncoder.encode(request.senha());

        // Instanciar aluno
        Aluno aluno = new Aluno(
                senhaCodificada,
                request.nome(),
                request.email(),
                universidade,
                request.img(),
                request.matricula(),
                curso,
                request.contato()
        );

        // Salvar aluno
        alunoRepository.save(aluno);

        // Retornar resposta
        return converterParaResponse(aluno);
    }

    // Listar todos os alunos
    @Transactional(readOnly = true)
    public List<AlunoResponse> listarTodos() {

        return alunoRepository.findAll()
                .stream()
                .map(this::converterParaResponse)
                .toList();
    }

    // Converter entidade para DTO Response
    private AlunoResponse converterParaResponse(Aluno aluno) {

        return new AlunoResponse(
                aluno.getId(),
                aluno.getNome(),
                aluno.getMatricula(),
                aluno.getCurso().getNome(),
                aluno.getEmail(),
                aluno.getContato(),
                aluno.getImg(),
                aluno.getStatus()
        );
    }
}