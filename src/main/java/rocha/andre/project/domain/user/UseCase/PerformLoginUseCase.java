package rocha.andre.project.domain.user.UseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import rocha.andre.project.domain.user.DTO.TokenJwtDTO;
import rocha.andre.project.domain.user.DTO.UserLoginDTO;
import rocha.andre.project.domain.user.User;
import rocha.andre.project.infra.security.TokenService;

@Component
public class PerformLoginUseCase {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    TokenService tokenService;

    public TokenJwtDTO performLogin(UserLoginDTO data) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(data.login(), data.senha());

        //está chamando authenticateService

        Authentication authentication = manager.authenticate(authenticationToken);

        var userAuthenticated = (User) authentication.getPrincipal();


        String tokenJwt = tokenService.generateJwtToken(userAuthenticated);

        return new TokenJwtDTO(tokenJwt);
    }
}
