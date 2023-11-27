package rocha.andre.project.domain.user.UseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocha.andre.project.domain.user.DTO.UserReturnDTO;
import rocha.andre.project.domain.user.UserRepository;
import rocha.andre.project.infra.exceptions.ValidationException;
import rocha.andre.project.infra.security.TokenService;

@Component
public class ListUserByIdUseCase {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository repository;

    public UserReturnDTO listUserById(String tokenJWT) {
        var userId = String.valueOf((tokenService.getUserIdFromToken(tokenJWT)).asInt());

        var user = repository.findByIdToHandle(userId);

        if (user == null) {
            throw new ValidationException("Não foram encontrados registros de usuário para o id fornecido.");
        }

        return new UserReturnDTO(user);
    }
}
