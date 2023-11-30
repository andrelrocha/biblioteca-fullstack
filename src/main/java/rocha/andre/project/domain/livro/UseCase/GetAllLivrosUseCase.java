package rocha.andre.project.domain.livro.UseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocha.andre.project.domain.livro.DTO.LivroReturnDTO;
import rocha.andre.project.domain.livro.LivroRepository;

import java.util.List;

@Component
public class GetAllLivrosUseCase {
    @Autowired
    private LivroRepository repository;

    public List<LivroReturnDTO> getAllLivros() {
        return repository.findAll().stream().map(LivroReturnDTO::new).toList();
    }

}
