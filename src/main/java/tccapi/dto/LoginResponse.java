package tccapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import tccapi.model.Enum.TipoPerfil;

@Getter
@AllArgsConstructor
public class LoginResponse {

    private String token;
    private String tipo;
    private Long id;
    private String nome;
    private TipoPerfil tipoPerfil;
    private String img;
}