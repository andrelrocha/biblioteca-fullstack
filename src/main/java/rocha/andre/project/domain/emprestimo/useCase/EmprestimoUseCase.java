package rocha.andre.project.domain.emprestimo.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocha.andre.project.domain.emprestimo.DTO.EmprestimoDTO;
import rocha.andre.project.domain.emprestimo.DTO.EmprestimoReturnDTO;
import rocha.andre.project.domain.emprestimo.Emprestimo;
import rocha.andre.project.domain.emprestimo.EmprestimoRepository;
import rocha.andre.project.domain.livro.LivroRepository;
import rocha.andre.project.domain.user.UserRepository;
import rocha.andre.project.infra.exceptions.ValidationException;
import rocha.andre.project.infra.security.TokenService;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Component
public class EmprestimoUseCase {
    @Autowired
    private EmprestimoRepository emprestimoRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private TokenService tokenService;

    public EmprestimoReturnDTO emprestimoLivro(EmprestimoDTO data, String tokenJWT) {
        var temEstoque = livroRepository.temEstoque(data.livro_id());

        if (!temEstoque) {
            throw new RuntimeException("O livro desejado não se encontra em estoque.");
        }

        var userIdString = String.valueOf((tokenService.getUserIdFromToken(tokenJWT)).asInt());
        var userId = Long.parseLong(userIdString);

        var user = userRepository.findById(userId)
                .orElseThrow(() -> new ValidationException("Não foi encontrado usuário para o id informado"));

        var livro = livroRepository.findById(data.livro_id())
                .orElseThrow(() -> new ValidationException("Não foi encontrado livro para o id informado"));

        var dataAtual = LocalDateTime.now();
        var diasParaAdicionar = 7;
        var dataDevolucao = adicionarDiasUteis(dataAtual, diasParaAdicionar);

        var emprestimo = new Emprestimo(user, livro, dataDevolucao);

        emprestimoRepository.save(emprestimo);

        livro.diminuiEstoque();

        return new EmprestimoReturnDTO(emprestimo);
    }

    private LocalDateTime adicionarDiasUteis(LocalDateTime dataAtual, int dias) {
        int diasContados = 0;

        while (diasContados < dias) {
            dataAtual = dataAtual.plusDays(1);

            if (dataAtual.getDayOfWeek() != DayOfWeek.SATURDAY && dataAtual.getDayOfWeek() != DayOfWeek.SUNDAY) {
                diasContados++;
            }
        }

        return dataAtual;
    }
}
