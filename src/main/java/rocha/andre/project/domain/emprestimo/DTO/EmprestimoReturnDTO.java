package rocha.andre.project.domain.emprestimo.DTO;

import rocha.andre.project.domain.emprestimo.Emprestimo;

import java.time.LocalDateTime;

public record EmprestimoReturnDTO(Long id,
                                  Long user_id,
                                  Long livro_id,
                                  LocalDateTime date) {

    public EmprestimoReturnDTO(Emprestimo emprestimo) {
        this(emprestimo.getId(), emprestimo.getUser().getId(), emprestimo.getLivro().getId(), emprestimo.getDate());
    }
}
