package tccapi.dto;

import java.time.LocalDateTime;
import java.util.List;

import tccapi.model.Orientador;
import tccapi.model.Enum.PreferenciaTema;
import tccapi.model.Enum.StatusUser;

public record OrientadorResponse(
        Long id,
        String nome,
        String email,
        String img,
        StatusUser status,
        Long universidadeId,
        List<String> areasAtuacao,
        PreferenciaTema perfilAceito,
        String descricao,
        Boolean aceitaOrientados,
        String contato,
        LocalDateTime criadoEm
) {
    public static OrientadorResponse from(Orientador o) {
        return new OrientadorResponse(
                o.getId(), o.getNome(), o.getEmail(), o.getImg(),
                o.getStatus(),
                o.getUniversidade() != null ? o.getUniversidade().getId() : null,
                o.getAreasAtuacao(), o.getPerfilAceito(), o.getDescricao(),
                o.getAceitaOrientados(), o.getContato(), o.getCriadoEm());
    }
}