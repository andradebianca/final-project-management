package tccapi.dto;

import tccapi.model.Aluno;

public record AlunoResponse(
        Long id,
        String nome,
        String matricula,
        String curso,
        String emailInstitucional,
        String statusCadastro,
        String ideiaTcc
) {
    public static AlunoResponse de(Aluno a) {
        String ideia = null;
        if (a.getIdeia() != null) {
            ideia = a.getIdeia().isSemIdeia()
                    ? "(sem ideia para TCC)"
                    : a.getIdeia().getTitulo();
        }
        return new AlunoResponse(
                a.getId(),
                a.getNome(),
                a.getMatricula(),
                a.getCurso(),
                a.getEmailInstitucional(),
                a.getStatusCadastro().name(),
                ideia
        );
    }
}