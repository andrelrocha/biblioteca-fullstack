package rocha.andre.project.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenValidationService {

    @Autowired
    private TokenService tokenService;

    @Value("${api.security.token.secret}")
    private String secret;

    public boolean isTokenValid(String tokenJwt) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            DecodedJWT jwt = JWT.require(algorithm)
                    .withIssuer("andre-rocha")
                    .build()
                    .verify(tokenJwt);

            var expiration = jwt.getExpiresAt().toInstant();
            var now = Instant.now();

            return !expiration.isBefore(now);

        } catch (JWTVerificationException | NullPointerException exception) {
            return false;
        }
    }
}
