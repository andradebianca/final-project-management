package tccapi.dto;

import jakarta.validation.constraints.NotNull;

public record IdeiaRequest(
        @NotNull Boolean semIdeia,
        String titulo,
        String descricao
) {}