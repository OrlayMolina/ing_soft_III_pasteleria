package co.edu.uniquindio.ing.soft.pasteleria.application.ports.input;

import co.edu.uniquindio.ing.soft.pasteleria.application.dto.request.CreateUserCommand;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.request.UpdateUserCommand;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.response.UserResponse;
import co.edu.uniquindio.ing.soft.pasteleria.domain.exception.DomainException;

import java.util.List;

public interface ManageUserUseCase {
    UserResponse createUser(CreateUserCommand command) throws DomainException;
    UserResponse updateUser(Long id, UpdateUserCommand command);
    void deleteUser(Long id);
    UserResponse getUser(Long id) throws DomainException;
    List<UserResponse> searchUser();
}
