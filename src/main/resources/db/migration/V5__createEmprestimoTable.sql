CREATE TABLE emprestimos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    livro_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (livro_id) REFERENCES livros(id)
);