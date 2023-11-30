package rocha.andre.project.domain.livro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    @Query("""
            SELECT CASE WHEN COUNT(l) > 0 THEN true 
            ELSE false END 
            FROM Livro l WHERE l.titulo = :titulo
            """)
    boolean livroExistsByTitulo(String titulo);

    @Query("""
            SELECT l FROM Livro l WHERE l.id = :id
            """)
    Livro findByIdToHandle(String id);

    @Query("""
            SELECT MAX(l.id) FROM Livro l
            """)
    long findLastId();

    @Query("""
            SELECT CASE WHEN l.quantidadeEstoque > 0 
            THEN true 
            ELSE false END 
            FROM Livro l WHERE l.id = :livroId
            """)
    boolean temEstoque(Long livroId);
}
