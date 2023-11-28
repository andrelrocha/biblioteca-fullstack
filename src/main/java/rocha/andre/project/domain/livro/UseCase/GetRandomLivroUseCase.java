package rocha.andre.project.domain.livro.UseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocha.andre.project.domain.livro.DTO.LivroReturnDTO;
import rocha.andre.project.domain.livro.LivroRepository;

import java.util.Random;

@Component
public class GetRandomLivroUseCase {
    @Autowired
    private LivroRepository repository;

    public LivroReturnDTO sugestaoLivro() {
        var lastId = repository.findLastId();

        Random random = new Random();
        long randomId = random.nextInt((int) lastId) + 1;

        var livroExists = repository.existsById(randomId);

        while (!livroExists) {
            randomId = random.nextInt((int) lastId) + 1;

            livroExists = repository.existsById(randomId);
        }

        var idSugestao = String.valueOf(randomId);
        var livroSugestao = repository.findByIdToHandle(idSugestao);

        return new LivroReturnDTO(livroSugestao);

    }
}
