package rocha.andre.project.domain.emprestimo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    @Query("""
        SELECT e 
        FROM Emprestimo e 
        WHERE e.user.id = :userId 
        AND e.retornado = false
        """)
    List<Emprestimo> allEmprestimosByUserIdWhereRetornadoIsFalse(Long userId);

    @Query("""
        SELECT COUNT(e) 
        FROM Emprestimo e 
        WHERE e.user.id = :userId
    """)
    Long countEmprestimosByUserId(Long userId);


    @Query("""
    SELECT SUM(e.valor) 
    FROM Emprestimo e 
    WHERE e.user.id = :userId
    """)
    Double getTotalValorByUserId(Long userId);

    @Query("""
    SELECT e 
    FROM Emprestimo e 
    WHERE e.livro.id = :id
    """)
    List<Emprestimo> findAllByLivroId(Long id);
}
