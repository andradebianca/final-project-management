package tccapi.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import tccapi.model.Enum.PreferenciaTema;

public record OrientadorRequest(
        @NotBlank @Size(min = 6) String senha,
        @NotBlank String nome,
        @NotBlank @Email String email,
        Long universidadeId,
        String img,
        @NotEmpty List<String> areasAtuacao,
        @NotNull PreferenciaTema perfilAceito,
        String descricao,
        @NotNull Boolean aceitaOrientados,
        String contato
) {}