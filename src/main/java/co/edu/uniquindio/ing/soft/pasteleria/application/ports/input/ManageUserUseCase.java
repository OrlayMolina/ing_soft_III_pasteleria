package co.edu.uniquindio.ing.soft.pasteleria.application.ports.input;

import co.edu.uniquindio.ing.soft.pasteleria.application.dto.MensajeDTO;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.request.CreateUserCommand;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.request.UpdateUserCommand;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.response.UserResponse;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.response.UserSimplifyResponse;
import co.edu.uniquindio.ing.soft.pasteleria.domain.exception.DomainException;

import java.util.List;

public interface ManageUserUseCase {
    MensajeDTO<UserResponse> createUser(CreateUserCommand command) throws DomainException;
    MensajeDTO<UserResponse> updateUser(Long id, UpdateUserCommand command);
    MensajeDTO<Void> deleteUser(Long id);
    MensajeDTO<UserResponse> getUser(Long id) throws DomainException;
    MensajeDTO<List<UserResponse>> searchUser();
    MensajeDTO<UserSimplifyResponse> getUserBasicInfo(Long id) throws DomainException;
}