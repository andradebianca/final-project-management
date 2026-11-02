package tccapi.dto;

import tccapi.model.Enum.StatusUser;

public record AlunoResponse(
        Long id,
        String nome,
        Integer matricula,
        String curso,
        String email,
        String contato,
        String img,
        StatusUser status
) {}