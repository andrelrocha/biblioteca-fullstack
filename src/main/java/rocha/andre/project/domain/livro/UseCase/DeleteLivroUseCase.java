package rocha.andre.project.domain.livro.UseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocha.andre.project.domain.livro.LivroRepository;
import rocha.andre.project.infra.exceptions.ValidationException;

@Component
public class DeleteLivroUseCase {
    @Autowired
    private LivroRepository repository;

    public void deleteLivro(long livroId) {
        var livroExists = repository.existsById(livroId);

        if (!livroExists) {
            throw new ValidationException("Não foi encontrado livro com o id informado para deleção");
        }

        repository.deleteById(livroId);
    }
}
