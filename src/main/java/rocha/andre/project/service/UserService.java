package rocha.andre.project.service;

import rocha.andre.project.domain.user.DTO.TokenJwtDTO;
import rocha.andre.project.domain.user.DTO.UserDTO;
import rocha.andre.project.domain.user.DTO.UserReturnDTO;

public interface UserService {
    TokenJwtDTO performLogin(UserDTO data);
    UserReturnDTO createUser(UserDTO data);
}
