package rocha.andre.project.infra.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocha.andre.project.domain.user.User;
import rocha.andre.project.domain.user.UserRepository;

@Component
public class AuthenticateUserWithValidJwt {
    @Autowired
    private UserRepository userRepository;

    public User findUserAuthenticated(String login) {
        return (User) userRepository.findByLogin(login);
    }

}
