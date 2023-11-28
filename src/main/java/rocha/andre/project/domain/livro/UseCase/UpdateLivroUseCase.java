package rocha.andre.project.domain.livro.UseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocha.andre.project.domain.livro.DTO.LivroReturnDTO;
import rocha.andre.project.domain.livro.DTO.UpdateLivroDTO;
import rocha.andre.project.domain.livro.LivroRepository;
import rocha.andre.project.infra.exceptions.ValidationException;
import rocha.andre.project.infra.security.TokenService;

@Component
public class UpdateLivroUseCase {
    @Autowired
    private LivroRepository repository;

    public LivroReturnDTO updateLivro(UpdateLivroDTO data, String livroId) {
        var livro = repository.findByIdToHandle(livroId);

        if (livro == null) {
            throw new ValidationException("Não foi encontrado livro para o id informado.");
        }

        livro.updateLivro(data);

        return new LivroReturnDTO(livro);
    }
}
