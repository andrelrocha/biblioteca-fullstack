package rocha.andre.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rocha.andre.project.domain.livro.DTO.LivroDTO;
import rocha.andre.project.domain.livro.DTO.LivroReturnDTO;
import rocha.andre.project.domain.livro.UseCase.CreateLivroUseCase;
import rocha.andre.project.domain.livro.UseCase.DeleteLivroUseCase;
import rocha.andre.project.domain.livro.UseCase.GetAllLivrosUseCase;
import rocha.andre.project.domain.livro.UseCase.GetRandomLivroUseCase;
import rocha.andre.project.service.LivroService;

import java.util.List;

@Service
public class LivroServiceImpl implements LivroService {
    @Autowired
    private CreateLivroUseCase createLivroUseCase;

    @Autowired
    private DeleteLivroUseCase deleteLivroUseCase;

    @Autowired
    private GetAllLivrosUseCase getAllLivrosUseCase;

    @Autowired
    private GetRandomLivroUseCase getRandomLivroUseCase;

    @Override
    public LivroReturnDTO createLivro(LivroDTO data) {
        var livro = createLivroUseCase.createLivro(data);
        return livro;
    }

    @Override
    public void deleteLivro(long livroId) {
        deleteLivroUseCase.deleteLivro(livroId);
    }

    @Override
    public List<LivroReturnDTO> getAllLivros() {
        var livros = getAllLivrosUseCase.getAllLivros();
        return livros;
    }

    @Override
    public LivroReturnDTO sugestaoLivro() {
        var livroSugestao = getRandomLivroUseCase.sugestaoLivro();
        return livroSugestao;
    }
}
