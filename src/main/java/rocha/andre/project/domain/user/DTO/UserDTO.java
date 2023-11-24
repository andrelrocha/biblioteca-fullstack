package rocha.andre.project.domain.user.DTO;

public record UserDTO(
        int matricula,
        String login,
        Role tipo
) {  }
