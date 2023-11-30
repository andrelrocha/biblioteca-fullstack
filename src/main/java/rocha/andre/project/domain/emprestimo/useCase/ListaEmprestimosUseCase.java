package rocha.andre.project.domain.emprestimo.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocha.andre.project.domain.emprestimo.DTO.EmprestimoListagemDTO;
import rocha.andre.project.domain.emprestimo.DTO.EmprestimoReturnDTO;
import rocha.andre.project.domain.emprestimo.Emprestimo;
import rocha.andre.project.domain.emprestimo.EmprestimoRepository;
import rocha.andre.project.domain.livro.Livro;
import rocha.andre.project.domain.livro.LivroRepository;
import rocha.andre.project.infra.security.TokenService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class ListaEmprestimosUseCase {
    @Autowired
    private EmprestimoRepository repository;
    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private TokenService tokenService;

    public ArrayList<EmprestimoListagemDTO> listaEmprestimos(String tokenJWT) {
        var userIdString = String.valueOf((tokenService.getUserIdFromToken(tokenJWT)).asInt());
        var userId = Long.parseLong(userIdString);

        var emprestimos = repository.allEmprestimosByUserId(userId);

        var emprestimosDTO = new ArrayList<EmprestimoListagemDTO>();
        var formatterOutput = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Emprestimo emprestimo : emprestimos) {
            var idString = String.valueOf(emprestimo.getLivro().getId());
            var livro = livroRepository.findByIdToHandle(idString);
            var tituloLivro = livro.getTitulo();

            var date = emprestimo.getDate();
            var dataFormatada = formatterOutput.format(date);

            var emprestimoDTO = new EmprestimoListagemDTO(
                    emprestimo.getId(),
                    emprestimo.getUser().getId(),
                    tituloLivro,
                    dataFormatada
            );

            emprestimosDTO.add(emprestimoDTO);
        }

        return emprestimosDTO;
    }
}
