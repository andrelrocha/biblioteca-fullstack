CREATE TABLE Livro (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    assunto VARCHAR(255),
    quantidadeEstoque INT NOT NULL
);