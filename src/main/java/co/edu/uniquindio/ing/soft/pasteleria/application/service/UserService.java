package co.edu.uniquindio.ing.soft.pasteleria.application.service;

import co.edu.uniquindio.ing.soft.pasteleria.application.dto.request.CreateUserCommand;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.request.UpdateUserCommand;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.response.UserResponse;
import co.edu.uniquindio.ing.soft.pasteleria.application.mapper.UserDtoMapper;
import co.edu.uniquindio.ing.soft.pasteleria.application.ports.input.ManageUserUseCase;
import co.edu.uniquindio.ing.soft.pasteleria.application.ports.output.UserPort;
import co.edu.uniquindio.ing.soft.pasteleria.domain.exception.DomainException;
import co.edu.uniquindio.ing.soft.pasteleria.domain.model.User;
import co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.entity.UserEntity;
import co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements ManageUserUseCase {
    private final UserPort userPort;
    private final UserDtoMapper userDtoMapper;
    private final UserJpaRepository userJpaRepository;

    @Override
    public UserResponse createUser(CreateUserCommand command) throws DomainException {
        Optional<UserEntity> userOptional = userJpaRepository.findByEmail(command.email());
        if(userOptional.isPresent()) {
            throw new DomainException("Ya existe un usuario con el mismo correo electrónico.");
        }

        User user = new User(
                command.typeDocument(),
                command.documentNumber(),
                command.phone(),
                command.position(),
                command.salary(),
                command.firstName(),
                command.secondName(),
                command.lastName(),
                command.secondLastName(),
                command.email(),
                command.password(),
                command.status(),
                command.isAdmin(),
                command.createdAt(),
                command.updatedAt()
        );

        try {
            User savedUser = userPort.saveUser(user);
            return userDtoMapper.toResponse(savedUser);
        } catch (DomainException e) {
            throw new RuntimeException("Error al crear el usuario: " + e.getMessage(), e);
        }
    }

    @Override
    public UserResponse updateUser(Long id, UpdateUserCommand command) {
        try {
            Optional<User> optionalUser = userPort.findUserById(id);
            if (optionalUser.isEmpty()) {
                throw new RuntimeException("El usuario con id " + id + " no existe.");
            }

            User existingUser = optionalUser.get();
            existingUser.setTypeDocument(command.typeDocument());
            existingUser.setPhone(command.phone());
            existingUser.setPosition(command.position());
            existingUser.setSalary(command.salary());
            existingUser.setFirstName(command.firstName());
            existingUser.setSecondName(command.secondName());
            existingUser.setLastName(command.lastName());
            existingUser.setSecondLastName(command.secondLastName());
            existingUser.setEmail(command.email());

            if (command.password() != null && !command.password().isEmpty()) {
                existingUser.setPassword(command.password());
            }
            existingUser.setStatus(command.status());
            existingUser.setUpdatedAt(command.updatedAt());

            User updatedUser = userPort.saveUser(existingUser);
            return userDtoMapper.toResponse(updatedUser);

        } catch (DomainException e) {
            throw new RuntimeException("Error al actualizar el usuario: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteUser(Long id) {
        userPort.deleteUserById(id);
    }

    @Override
    public UserResponse getUser(Long id) throws DomainException {
        Optional<User> optionalUser = userPort.findUserById(id);
        if (optionalUser.isEmpty()) {
            return null;
        }
        return userDtoMapper.toResponse(optionalUser.get());
    }

    @SneakyThrows
    @Override
    public List<UserResponse> searchUser() {
        List<User> users = userPort.findAllUsers();
        return users.stream().map(user -> {
            try {
                return userDtoMapper.toResponse(user);
            } catch (DomainException e) {
                throw new RuntimeException("Error al mapear User a UserResponse", e);
            }
        }).toList();
    }
}
