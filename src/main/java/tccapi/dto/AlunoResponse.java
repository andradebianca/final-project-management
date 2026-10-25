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
        String status = "PENDENTE";

        return new AlunoResponse(
                a.getId(),
                a.getNome(), // <-- Aqui está correto (herdado de Usuario)
                a.getMatricula(),
                a.getCurso(),
                a.getEmailInstitucional(),
                status,
                ideia
        );
    }
}