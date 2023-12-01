package rocha.andre.project.domain.emprestimo.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocha.andre.project.domain.emprestimo.DTO.EmprestimosLivroDTO;
import rocha.andre.project.domain.emprestimo.Emprestimo;
import rocha.andre.project.domain.emprestimo.EmprestimoRepository;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Component
public class ListaEmprestimosLivroUseCase {
    @Autowired
    private EmprestimoRepository repository;

    public ArrayList<EmprestimosLivroDTO> getAllEmprestimosByLivroId(Long livroId) {
        var emprestimos = repository.findAllByLivroId(livroId);
        var emprestimosDTO = new ArrayList<EmprestimosLivroDTO>();
        var formatterOutput = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Emprestimo emprestimo : emprestimos) {
            var dataFormatada = formatterOutput.format(emprestimo.getDate());

            var emprestimoDTO = new EmprestimosLivroDTO(
                    emprestimo.getId(),
                    emprestimo.getUser().getId(),
                    dataFormatada
            );

            emprestimosDTO.add(emprestimoDTO);
        }

        return emprestimosDTO;
    }

}
