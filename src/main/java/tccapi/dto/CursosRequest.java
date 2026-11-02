package tccapi.dto;

import jakarta.validation.constraints.NotBlank;

public record CursosRequest(

        @NotBlank
        String nome

) {}