package rocha.andre.project.domain.livro.UseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocha.andre.project.domain.livro.LivroRepository;

@Component
public class GetAllLivrosUseCase {
    @Autowired
    private LivroRepository repository;

}
