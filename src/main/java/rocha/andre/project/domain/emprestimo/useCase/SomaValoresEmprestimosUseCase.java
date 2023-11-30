package rocha.andre.project.domain.emprestimo.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocha.andre.project.domain.emprestimo.EmprestimoRepository;
import rocha.andre.project.infra.security.TokenService;

@Component
public class SomaValoresEmprestimosUseCase {
    @Autowired
    private EmprestimoRepository repository;
    @Autowired
    private TokenService tokenService;

    public double GetValorTotalEmprestimo(String tokenJWT) {
        var userIdString = String.valueOf((tokenService.getUserIdFromToken(tokenJWT)).asInt());
        var userId = Long.parseLong(userIdString);

        return repository.getTotalValorByUserId(userId);
    }

}
