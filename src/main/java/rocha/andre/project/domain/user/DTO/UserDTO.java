package rocha.andre.project.domain.user.DTO;

public record UserDTO(
        int matricula,
        String login,
        String senha,
        Role tipo
) {  }
