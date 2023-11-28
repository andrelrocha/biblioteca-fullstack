package rocha.andre.project.domain.livro;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import rocha.andre.project.domain.livro.DTO.LivroDTO;
import rocha.andre.project.domain.livro.DTO.UpdateLivroDTO;

@Table(name = "livros")
@Entity(name = "Livro")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titulo;
    private String autor;
    private String assunto;
    private int quantidadeEstoque;

    public Livro(LivroDTO data) {
        this.titulo = data.titulo();
        this.autor = data.autor();
        this.assunto = data.assunto();
        this.quantidadeEstoque = data.quantidadeEstoque();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public void updateLivro(UpdateLivroDTO data) {
        if (data.titulo() != null) {
            this.titulo = data.titulo();
        }

        if (data.autor() != null) {
            this.autor = data.autor();
        }

        if (data.assunto() != null) {
            this.assunto = data.assunto();
        }

        if (data.quantidadeEstoque() != 0) {
            this.quantidadeEstoque = data.quantidadeEstoque();
        }
    }
}
