package rocha.andre.project.domain.user.DTO;

import rocha.andre.project.domain.user.User;

public record UserReturnDTO(Long id, String login, int matricula, Role tipo, double saldo) {
    public UserReturnDTO(User user) {
        this(user.getId(), user.getLogin(), user.getMatricula(), user.getTipo(), user.getSaldo());
    }
}
