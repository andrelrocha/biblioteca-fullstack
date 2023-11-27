package rocha.andre.project.domain.user.UseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocha.andre.project.domain.user.DTO.UserReturnDTO;
import rocha.andre.project.domain.user.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListAllUsersUseCase {
    @Autowired
    private UserRepository repository;

    public List<UserReturnDTO> listAllUsers() {
        return repository.findAll().stream()
                .map(UserReturnDTO::new)
                .collect(Collectors.toList());
    }
}
