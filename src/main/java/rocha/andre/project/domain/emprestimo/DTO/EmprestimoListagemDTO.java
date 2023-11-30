package rocha.andre.project.domain.emprestimo.DTO;

public record EmprestimoListagemDTO(Long id,
                                    Long user_id,
                                    String livro_titulo,
                                    String date) {
}
