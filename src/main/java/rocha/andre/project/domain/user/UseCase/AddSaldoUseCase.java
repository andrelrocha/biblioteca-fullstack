package rocha.andre.project.domain.user.UseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocha.andre.project.domain.user.DTO.SaldoDTO;
import rocha.andre.project.domain.user.UserRepository;
import rocha.andre.project.infra.security.TokenService;

@Component
public class AddSaldoUseCase {
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;

    public void addSaldo(SaldoDTO data, String tokenJWT) {
        var valor = data.valor();
        var userId = String.valueOf((tokenService.getUserIdFromToken(tokenJWT)).asInt());
        var user = repository.findByIdToHandle(userId);

        var saldoAtual = user.getSaldo();
        var novoSaldo = valor + saldoAtual;
        user.setSaldo(novoSaldo);
    }
}
