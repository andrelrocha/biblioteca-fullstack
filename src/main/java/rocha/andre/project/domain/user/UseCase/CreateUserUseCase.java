package rocha.andre.project.domain.user.UseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import rocha.andre.project.domain.user.DTO.UserDTO;
import rocha.andre.project.domain.user.DTO.UserReturnDTO;
import rocha.andre.project.domain.user.User;
import rocha.andre.project.domain.user.UserRepository;
import rocha.andre.project.infra.exceptions.ValidationException;

import java.util.Random;

@Component
public class CreateUserUseCase {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CSVExporterUser csvExporterUser;

    public UserReturnDTO createUser(UserDTO data) {
        boolean userExistsByLogin = userRepository.userExistsByLogin(data.login());

        if (userExistsByLogin) {
            throw new ValidationException("Já existe uma conta cadastrada com o login informado.");
        }

        var matriculaInt = geraMatricula();
        var newUser = new User(data, matriculaInt);

        String encodedPassword = bCryptPasswordEncoder.encode(data.senha());
        newUser.setPassword(encodedPassword);

        var userOnDb = userRepository.save(newUser);

        csvExporterUser.exportUsersToCSV();

        return new UserReturnDTO(userOnDb);
    }

    public int geraMatricula() {
        var random = new Random();
        var matricula = String.valueOf(random.nextInt(100000) + 1);
        boolean userExistsByMatricula = userRepository.userExistsByMatricula(matricula);
        while (userExistsByMatricula) {
            matricula = String.valueOf(random.nextInt(100000) + 1);
            userExistsByMatricula = userRepository.userExistsByMatricula(matricula);
        }

        return Integer.parseInt(matricula);
    }
}
