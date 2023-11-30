package rocha.andre.project.domain.emprestimo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    @Query("""
    SELECT e 
    FROM Emprestimo e 
    WHERE e.user.id = :userId
    """)
    List<Emprestimo> allEmprestimosByUserId(Long userId);
}
