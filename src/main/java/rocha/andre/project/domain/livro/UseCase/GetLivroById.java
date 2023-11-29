package rocha.andre.project.domain.livro.UseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocha.andre.project.domain.livro.DTO.LivroReturnDTO;
import rocha.andre.project.domain.livro.LivroRepository;
import rocha.andre.project.infra.exceptions.ValidationException;

@Component
public class GetLivroById {
    @Autowired
    private LivroRepository repository;

    public LivroReturnDTO getLivroById(long livroId) {
        var livro = repository.findById(livroId)
                                            .orElseThrow(() -> new ValidationException("Não foi encontrado livro com o id informado nos parametros"));

        return new LivroReturnDTO(livro);
    }
}
