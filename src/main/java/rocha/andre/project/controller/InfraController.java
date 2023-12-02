package rocha.andre.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rocha.andre.project.infra.security.TokenValidationService;

@RestController
@RequestMapping("/infra")
public class InfraController {
    @Autowired
    private TokenValidationService tokenValidationService;

    @GetMapping("/verifyjwt/{tokenJWT}")
    public boolean validateToken(@PathVariable String tokenJWT) {
        var isValid = tokenValidationService.isTokenValid(tokenJWT);
        return isValid;
    }
}
