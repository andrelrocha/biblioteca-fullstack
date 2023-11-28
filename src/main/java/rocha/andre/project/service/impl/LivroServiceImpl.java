package rocha.andre.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rocha.andre.project.domain.livro.DTO.LivroDTO;
import rocha.andre.project.domain.livro.DTO.LivroReturnDTO;
import rocha.andre.project.domain.livro.UseCase.CreateLivroUseCase;
import rocha.andre.project.domain.livro.UseCase.GetRandomLivroUseCase;
import rocha.andre.project.service.LivroService;

@Service
public class LivroServiceImpl implements LivroService {
    @Autowired
    private CreateLivroUseCase createLivroUseCase;

    @Autowired
    private GetRandomLivroUseCase getRandomLivroUseCase;

    @Override
    public LivroReturnDTO createLivro(LivroDTO data) {
        var livro = createLivroUseCase.createLivro(data);
        return livro;
    }

    @Override
    public LivroReturnDTO sugestaoLivro() {
        var livroSugestao = getRandomLivroUseCase.sugestaoLivro();
        return livroSugestao;
    }
}
