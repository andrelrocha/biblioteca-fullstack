package rocha.andre.project.domain.livro.UseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocha.andre.project.domain.livro.DTO.LivroDTO;
import rocha.andre.project.domain.livro.DTO.LivroReturnDTO;
import rocha.andre.project.domain.livro.Livro;
import rocha.andre.project.domain.livro.LivroRepository;
import rocha.andre.project.infra.exceptions.ValidationException;

@Component
public class CreateLivroUseCase {
    @Autowired
    private LivroRepository repository;

    public LivroReturnDTO createLivro(LivroDTO data) {
        var livroExists = repository.livroExistsByTitulo(data.titulo());

        if (livroExists) {
            throw new ValidationException("Já existe um livro cadastrado com este título");
        }

        var livro = new Livro(data);

        var livroOnDb = repository.save(livro);

        return new LivroReturnDTO(livroOnDb);
    }
}
