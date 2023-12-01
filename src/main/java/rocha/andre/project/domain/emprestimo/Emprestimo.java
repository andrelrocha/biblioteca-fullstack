package rocha.andre.project.domain.emprestimo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import rocha.andre.project.domain.livro.Livro;
import rocha.andre.project.domain.user.User;

import java.time.LocalDateTime;

@Table(name = "emprestimos")
@Entity(name = "Emprestimo")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "livro_id")
    private Livro livro;

    private LocalDateTime date;

    private int valor;

    private boolean retornado;

    public Emprestimo(User user, Livro livro, LocalDateTime date) {
        this.livro = livro;
        this.user = user;
        this.date = date;
        this.valor = 2;
        this.retornado = false;
    }

    public void multaAtraso() {
        this.valor++;
    }
}
