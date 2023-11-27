package rocha.andre.project.domain.user.DTO;

public record UserDTO(
        String login,
        String senha,
        Role tipo
) {  }
