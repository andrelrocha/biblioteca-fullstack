package rocha.andre.project.domain.livro.DTO;

import rocha.andre.project.domain.livro.Livro;

public record LivroReturnDTO(long id, String titulo, String autor, String assunto, int quantidadeEstoque) {
    public LivroReturnDTO(Livro livro) {
        this(livro.getId(), livro.getTitulo(), livro.getAutor(), livro.getAssunto(), livro.getQuantidadeEstoque());
    }
}
