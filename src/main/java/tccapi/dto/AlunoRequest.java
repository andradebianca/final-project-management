package tccapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AlunoRequest(

    @NotBlank
    String senha,

    @NotBlank
    String nome,

    @NotBlank
    @Email
    String email,

    @NotNull
    Long universidadeId,

    String img,

    @NotNull
    @Positive
    Integer matricula,

    @NotNull
    Long cursoId,

    String contato

) {}