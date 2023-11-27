package rocha.andre.project.service;

import rocha.andre.project.domain.user.DTO.TokenJwtDTO;
import rocha.andre.project.domain.user.DTO.UserDTO;
import rocha.andre.project.domain.user.DTO.UserLoginDTO;
import rocha.andre.project.domain.user.DTO.UserReturnDTO;

import java.util.List;

public interface UserService {
    TokenJwtDTO performLogin(UserLoginDTO data);
    UserReturnDTO createUser(UserDTO data);
    List<UserReturnDTO> listAllUsers();
    UserReturnDTO listUserById(String tokenJWT);
}
