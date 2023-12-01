package rocha.andre.project.domain.emprestimo.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocha.andre.project.domain.emprestimo.DTO.EmprestimoRetornarDTO;
import rocha.andre.project.domain.emprestimo.EmprestimoRepository;
import rocha.andre.project.domain.livro.LivroRepository;
import rocha.andre.project.domain.user.UserRepository;
import rocha.andre.project.infra.exceptions.ValidationException;
import rocha.andre.project.infra.security.TokenService;

import java.time.LocalDateTime;

@Component
public class RetornarLivroUseCase {
    @Autowired
    private EmprestimoRepository emprestimoRepository;
    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    public void retornarLivro(EmprestimoRetornarDTO data, String tokenJWT) {
        var userId = String.valueOf((tokenService.getUserIdFromToken(tokenJWT)).asLong());
        var user = userRepository.findByIdToHandle(userId);
        if (user == null) {
            throw new ValidationException("Não foi encontrado usuário para o id informado.");
        }

        var saldo = user.getSaldo();

        var emprestimo = emprestimoRepository.findById(data.id())
                .orElseThrow(() ->  new ValidationException("Não foi encontrado o livro para o id informado."));

        var now = LocalDateTime.now();
        var emprestimoDataEntrega = emprestimo.getDate();

        if (now.isAfter(emprestimoDataEntrega)) {
            emprestimo.multaAtraso();
        }

        var novoSaldo = saldo - emprestimo.getValor();

        user.setSaldo(novoSaldo);

        var livro = livroRepository.findByTituloToHandle(data.titulo());
        livro.aumentaEstoque();

        emprestimoRepository.deleteById(data.id());
    }
}
