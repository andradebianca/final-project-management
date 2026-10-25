package tccapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import tccapi.model.Enum.Perfil;

@Entity
@Table(name = "administrador")
public class Administrador extends Usuario {

    protected Administrador() {}

    public Administrador(String nome, String login, String senhaCriptografada) {
        super(nome, login, senhaCriptografada, Perfil.ADMINISTRADOR);
    }
}