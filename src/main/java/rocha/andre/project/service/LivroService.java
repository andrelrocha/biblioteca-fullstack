package rocha.andre.project.service;

import rocha.andre.project.domain.livro.DTO.LivroDTO;
import rocha.andre.project.domain.livro.DTO.LivroReturnDTO;

public interface LivroService {
    LivroReturnDTO createLivro(LivroDTO data);
}
