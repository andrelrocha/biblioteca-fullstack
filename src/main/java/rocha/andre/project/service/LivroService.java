package rocha.andre.project.service;

import rocha.andre.project.domain.livro.DTO.LivroDTO;
import rocha.andre.project.domain.livro.DTO.LivroReturnDTO;

import java.util.List;

public interface LivroService {
    LivroReturnDTO createLivro(LivroDTO data);
    void deleteLivro(long livroId);
    List<LivroReturnDTO> getAllLivros();


    LivroReturnDTO sugestaoLivro();
}
