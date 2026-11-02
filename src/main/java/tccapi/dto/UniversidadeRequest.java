package tccapi.dto;

import jakarta.validation.constraints.NotBlank;

public record UniversidadeRequest(
    @NotBlank
    String nome

) {}