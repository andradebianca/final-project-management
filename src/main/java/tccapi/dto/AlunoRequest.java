package tccapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AlunoRequest(
        @NotBlank String nomeCompleto,
        @NotBlank String matricula,
        @NotBlank String curso,
        @NotBlank @Email String emailInstitucional,
        @NotBlank @Size(min = 8) String senha
) {}