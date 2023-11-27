package rocha.andre.project.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rocha.andre.project.domain.user.DTO.TokenJwtDTO;
import rocha.andre.project.domain.user.DTO.UserDTO;
import rocha.andre.project.domain.user.DTO.UserLoginDTO;
import rocha.andre.project.domain.user.DTO.UserReturnDTO;
import rocha.andre.project.domain.user.UseCase.CreateUserUseCase;
import rocha.andre.project.domain.user.UseCase.ListAllUsersUseCase;
import rocha.andre.project.domain.user.UseCase.PerformLoginUseCase;
import rocha.andre.project.service.UserService;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private CreateUserUseCase createUserUseCase;
    @Autowired
    private ListAllUsersUseCase listAllUsersUseCase;
    @Autowired
    private PerformLoginUseCase performLoginUseCase;

    @Override
    public TokenJwtDTO performLogin(UserLoginDTO data) {
        var tokenJwt = performLoginUseCase.performLogin(data);
        return tokenJwt;
    }

    @Override
    public UserReturnDTO createUser(UserDTO data) {
        var user = createUserUseCase.createUser(data);
        return user;
    }

    @Override
    public List<UserReturnDTO> listAllUsers() {
        var users = listAllUsersUseCase.listAllUsers();
        return users;
    }
}
