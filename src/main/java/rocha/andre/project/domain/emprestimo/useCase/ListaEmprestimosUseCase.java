package rocha.andre.project.domain.emprestimo.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocha.andre.project.domain.emprestimo.DTO.EmprestimoReturnDTO;
import rocha.andre.project.domain.emprestimo.EmprestimoRepository;
import rocha.andre.project.infra.security.TokenService;

import java.util.List;

@Component
public class ListaEmprestimosUseCase {
    @Autowired
    private EmprestimoRepository repository;
    @Autowired
    private TokenService tokenService;

    public List<EmprestimoReturnDTO> listaEmprestimos(String tokenJWT) {
        var userIdString = String.valueOf((tokenService.getUserIdFromToken(tokenJWT)).asInt());
        var userId = Long.parseLong(userIdString);

        return repository.allEmprestimosByUserId(userId).stream().map(EmprestimoReturnDTO::new).toList();
    }
}
