package tccapi.model.Enum;

public enum Grupo {
    INDIVIDUAL(1),
    DUPLA(2);

    private final int quantidadeAlunos;

    Grupo(int quantidadeAlunos) {
        this.quantidadeAlunos = quantidadeAlunos;
    }

    public int getQuantidadeAlunos() { return quantidadeAlunos; }
}