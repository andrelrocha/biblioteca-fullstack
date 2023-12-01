package rocha.andre.project.service;

import rocha.andre.project.domain.user.DTO.*;

import java.util.List;

public interface UserService {
    String addSaldo(SaldoDTO valor, String tokenJWT);
    TokenJwtDTO performLogin(UserLoginDTO data);
    UserReturnDTO createUser(UserDTO data);
    List<UserReturnDTO> listAllUsers();
    UserReturnDTO listUserById(String tokenJWT);
    UserReturnDTO updateUser(UserUpdateDTO data, String tokenJWT);
}
