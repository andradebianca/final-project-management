package tccapi.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tccapi.dto.AlunoRequest;
import tccapi.dto.AlunoResponse;
import tccapi.dto.IdeiaRequest;
import tccapi.model.Aluno;
import tccapi.model.IdeiaTcc;
import tccapi.model.StatusCadastro;
import tccapi.repository.AlunoRepository;

@Service
public class AlunoService {

    private final AlunoRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AlunoService(AlunoRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    // Etapa 1: cadastra o aluno (status PENDENTE_IDEIA)
    @Transactional
    public AlunoResponse cadastrar(AlunoRequest req) {
        if (repository.existsByMatricula(req.matricula())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Matrícula já cadastrada");
        }
        if (repository.existsByEmailInstitucional(req.emailInstitucional())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já cadastrado");
        }

        Aluno aluno = new Aluno(
                req.nomeCompleto(),
                req.matricula(),
                req.curso(),
                req.emailInstitucional(),
                passwordEncoder.encode(req.senha())
        );
        return AlunoResponse.de(repository.save(aluno));
    }

    // Etapa 2: registra a ideia (ou "sem ideia") e finaliza o cadastro
    @Transactional
    public AlunoResponse registrarIdeia(Long alunoId, IdeiaRequest req) {
        Aluno aluno = repository.findById(alunoId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Aluno não encontrado"));

        if (aluno.getStatusCadastro() == StatusCadastro.COMPLETO) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cadastro já finalizado");
        }

        if (!req.semIdeia()) {
            boolean faltando = req.titulo() == null || req.titulo().isBlank()
                    || req.descricao() == null || req.descricao().isBlank();
            if (faltando) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Informe o nome do TCC e a descrição da ideia");
            }
        }

        IdeiaTcc ideia = req.semIdeia()
                ? new IdeiaTcc(aluno, true, null, null)
                : new IdeiaTcc(aluno, false, req.titulo(), req.descricao());

        aluno.registrarIdeia(ideia);
        return AlunoResponse.de(repository.save(aluno));
    }

    @Transactional(readOnly = true)
    public AlunoResponse buscar(Long id) {
        return repository.findById(id)
                .map(AlunoResponse::de)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Aluno não encontrado"));
    }
}