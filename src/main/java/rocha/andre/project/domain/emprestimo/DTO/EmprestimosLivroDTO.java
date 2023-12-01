package rocha.andre.project.domain.emprestimo.DTO;

public record EmprestimosLivroDTO(Long id,
                                  Long user_id,
                                  String date,
                                  boolean retornado) {
}
