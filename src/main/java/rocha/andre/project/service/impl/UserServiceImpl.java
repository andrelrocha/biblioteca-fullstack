package rocha.andre.project.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rocha.andre.project.domain.user.DTO.*;
import rocha.andre.project.domain.user.UseCase.*;
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
    private ListUserByIdUseCase listUserByIdUseCase;
    @Autowired
    private PerformLoginUseCase performLoginUseCase;
    @Autowired
    private UpdateUserUseCase updateUserUseCase;

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

    @Override
    public UserReturnDTO listUserById(String tokenJWT) {
        var user = listUserByIdUseCase.listUserById(tokenJWT);
        return user;
    }

    @Override
    public UserReturnDTO updateUser(UserUpdateDTO data, String tokenJWT) {
        var userUpdated = updateUserUseCase.updateUser(data, tokenJWT);
        return userUpdated;
    }
}
