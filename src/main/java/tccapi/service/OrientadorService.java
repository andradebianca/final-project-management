package tccapi.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import tccapi.dto.OrientadorRequest;
import tccapi.dto.OrientadorResponse;
import tccapi.model.Orientador;
import tccapi.model.Universidade;
import tccapi.model.Enum.StatusUser;
import tccapi.repository.OrientadorRepository;
import tccapi.repository.UniversidadeRepository;

@Service
public class OrientadorService {

    private final OrientadorRepository orientadorRepository;
    private final UniversidadeRepository universidadeRepository;
    private final PasswordEncoder passwordEncoder;

    public OrientadorService(OrientadorRepository orientadorRepository,
                             UniversidadeRepository universidadeRepository,
                             PasswordEncoder passwordEncoder) {
        this.orientadorRepository = orientadorRepository;
        this.universidadeRepository = universidadeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public OrientadorResponse criar(OrientadorRequest req) {
        if (orientadorRepository.existsByEmail(req.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já em uso");
        }

        Orientador o = new Orientador(
                req.areasAtuacao(),
                req.perfilAceito(),
                req.descricao(),
                req.aceitaOrientados(),
                req.contato());

        o.setSenha(passwordEncoder.encode(req.senha()));
        o.setStatus(StatusUser.ATIVO);
        preencherDadosUsuario(o, req);

        return OrientadorResponse.from(orientadorRepository.save(o));
    }

    @Transactional(readOnly = true)
    public List<OrientadorResponse> listar() {
        return orientadorRepository.findAll().stream()
                .map(OrientadorResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public OrientadorResponse buscarPorId(Long id) {
        return OrientadorResponse.from(buscarEntidade(id));
    }

    @Transactional
    public OrientadorResponse atualizar(Long id, OrientadorRequest req) {
        Orientador o = buscarEntidade(id);

        if (!o.getEmail().equals(req.email()) && orientadorRepository.existsByEmail(req.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já em uso");
        }

        // a senha é ignorada na atualização
        preencherDadosUsuario(o, req);
        o.setAreasAtuacao(req.areasAtuacao());
        o.setPerfilAceito(req.perfilAceito());
        o.setDescricao(req.descricao());
        o.setAceitaOrientados(req.aceitaOrientados());
        o.setContato(req.contato());

        return OrientadorResponse.from(orientadorRepository.save(o));
    }

    @Transactional
    public void inativar(Long id) {
        Orientador o = buscarEntidade(id);
        o.setStatus(StatusUser.INATIVO);
        orientadorRepository.save(o);
    }

    // campos herdados de Usuario
    private void preencherDadosUsuario(Orientador o, OrientadorRequest req) {
        o.setNome(req.nome());
        o.setEmail(req.email());
        o.setImg(req.img());

        if (req.universidadeId() != null) {
            Universidade u = universidadeRepository.findById(req.universidadeId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Universidade não encontrada"));
            o.setUniversidade(u);
        } else {
            o.setUniversidade(null);
        }
    }

    private Orientador buscarEntidade(Long id) {
        return orientadorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Orientador não encontrado"));
    }
}