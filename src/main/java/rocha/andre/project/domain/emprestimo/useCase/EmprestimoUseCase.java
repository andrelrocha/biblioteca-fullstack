package rocha.andre.project.domain.emprestimo.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocha.andre.project.domain.emprestimo.DTO.EmprestimoDTO;
import rocha.andre.project.domain.emprestimo.DTO.EmprestimoReturnDTO;
import rocha.andre.project.domain.emprestimo.Emprestimo;
import rocha.andre.project.domain.emprestimo.EmprestimoRepository;
import rocha.andre.project.domain.livro.LivroRepository;
import rocha.andre.project.domain.user.User;
import rocha.andre.project.domain.user.UserRepository;
import rocha.andre.project.infra.exceptions.DontHaveEnoughFunds;
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
        var livroId = data.livro_id();
        validaEstoque(livroId);

        var userId = (tokenService.getUserIdFromToken(tokenJWT)).asLong();

        var user = userRepository.findById(userId)
                .orElseThrow(() -> new ValidationException("Não foi encontrado usuário para o id informado"));

        validaSaldo(user);

        var livro = livroRepository.findById(data.livro_id())
                .orElseThrow(() -> new ValidationException("Não foi encontrado livro para o id informado"));

        var dataDevolucao = calculaDias();

        var emprestimo = new Emprestimo(user, livro, dataDevolucao);

        emprestimoRepository.save(emprestimo);

        livro.diminuiEstoque();

        return new EmprestimoReturnDTO(emprestimo);
    }

    private void validaEstoque(Long livroId) {
        var temEstoque = livroRepository.temEstoque(livroId);

        if (!temEstoque) {
            throw new RuntimeException("O livro desejado não se encontra em estoque.");
        }
    }

    private void validaSaldo(User user) {
        var quantidadeEmprestimos = emprestimoRepository.countEmprestimosByUserId(user.getId());
        var valorEmprestimoComMulta = 3.00;
        var valorEmprestimoMax = (quantidadeEmprestimos*valorEmprestimoComMulta) + valorEmprestimoComMulta;


        var saldoUser = user.getSaldo();
        if (saldoUser < valorEmprestimoMax) {
            throw new DontHaveEnoughFunds("Usuário não possui saldo suficiente para a transação.");
        }
    }

    private LocalDateTime calculaDias() {
        var dataAtual = LocalDateTime.now();
        var diasParaAdicionar = 7;
        var dataDevolucao = adicionarDiasUteis(dataAtual, diasParaAdicionar);
        return dataDevolucao;
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
