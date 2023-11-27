package rocha.andre.project.domain.user.UseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import rocha.andre.project.domain.user.DTO.UserDTO;
import rocha.andre.project.domain.user.DTO.UserReturnDTO;
import rocha.andre.project.domain.user.User;
import rocha.andre.project.domain.user.UserRepository;
import rocha.andre.project.infra.exceptions.ValidationException;

@Component
public class CreateUserUseCase {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CSVExporterUser csvExporterUser;

    public UserReturnDTO createUser(UserDTO data) {
        var matriculaString = String.valueOf(data.matricula());
        boolean userExistsByMatricula = userRepository.userExistsByMatricula(matriculaString);
        boolean userExistsByLogin = userRepository.userExistsByLogin(data.login());

        if (userExistsByMatricula) {
            throw new ValidationException("Já existe uma conta cadastrada com a matricula informada.");
        }

        if (userExistsByLogin) {
            throw new ValidationException("Já existe uma conta cadastrada com o login informado.");
        }

        var newUser = new User(data);

        String encodedPassword = bCryptPasswordEncoder.encode(data.senha());
        newUser.setPassword(encodedPassword);

        var userOnDb = userRepository.save(newUser);

        csvExporterUser.exportUsersToCSV();

        return new UserReturnDTO(userOnDb);
    }
}
