package co.edu.uniquindio.ing.soft.pasteleria.application.service;

import co.edu.uniquindio.ing.soft.pasteleria.application.dto.MensajeDTO;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.request.CreateUserCommand;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.request.UpdateUserCommand;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.response.PageResponse;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.response.SupplyResponse;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.response.UserResponse;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.response.UserSimplifyResponse;
import co.edu.uniquindio.ing.soft.pasteleria.application.mapper.UserDtoMapper;
import co.edu.uniquindio.ing.soft.pasteleria.application.ports.input.ManageUserUseCase;
import co.edu.uniquindio.ing.soft.pasteleria.application.ports.output.UserPort;
import co.edu.uniquindio.ing.soft.pasteleria.domain.exception.DomainException;
import co.edu.uniquindio.ing.soft.pasteleria.domain.model.Supply;
import co.edu.uniquindio.ing.soft.pasteleria.domain.model.User;
import co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.entity.UserEntity;
import co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.adapter.config.CryptoPassword.encriptarPassword;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements ManageUserUseCase {
    private final UserPort userPort;
    private final UserDtoMapper userDtoMapper;
    private final UserJpaRepository userJpaRepository;

    @Override
    public MensajeDTO<String> createUser(CreateUserCommand command) throws DomainException {
        try {
            Optional<UserEntity> userOptional = userJpaRepository.findByEmail(command.email());
            if (userOptional.isPresent()) {
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
                    encriptarPassword(command.password()),
                    command.status(),
                    command.isAdmin(),
                    command.createdAt(),
                    command.updatedAt()
            );

            userPort.saveUser(user);
            return new MensajeDTO<>(false, "usuario creado con exito");
        } catch (Exception e) {
            return new MensajeDTO<>(true, "Error al crear el usuario: " + e.getMessage());
        }
    }

    @Override
    public MensajeDTO<UserResponse> updateUser(Long id, UpdateUserCommand command) {
        try {
            Optional<User> optionalUser = userPort.findUserById(id);
            if (optionalUser.isEmpty()) {
                return new MensajeDTO<>(true, null);
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
            UserResponse response = userDtoMapper.toResponse(updatedUser);

            return new MensajeDTO<>(false, response);
        } catch (DomainException e) {
            return new MensajeDTO<>(true, null);
        } catch (Exception e) {
            return new MensajeDTO<>(true, null);
        }
    }

    @Override
    public MensajeDTO<Void> deleteUser(Long id) {
        try {
            userPort.deleteUserById(id);
            return new MensajeDTO<>(false, null);
        } catch (Exception e) {
            return new MensajeDTO<>(true, null);
        }
    }

    @Override
    public MensajeDTO<UserResponse> getUser(Long id) throws DomainException {
        try {
            Optional<User> optionalUser = userPort.findUserById(id);
            if (optionalUser.isEmpty()) {
                return new MensajeDTO<>(true, null);
            }
            UserResponse response = userDtoMapper.toResponse(optionalUser.get());
            return new MensajeDTO<>(false, response);
        } catch (DomainException e) {
            return new MensajeDTO<>(true, null);
        } catch (Exception e) {
            return new MensajeDTO<>(true, null);
        }
    }

    @Override
    public MensajeDTO<List<UserResponse>> searchUser() {
        try {
            List<User> users = userPort.findAllUsers();
            List<UserResponse> responses = users.stream().map(user -> {
                try {
                    return userDtoMapper.toResponse(user);
                } catch (DomainException e) {
                    throw new RuntimeException("Error al mapear User a UserResponse", e);
                }
            }).toList();

            return new MensajeDTO<>(false, responses);
        } catch (Exception e) {
            return new MensajeDTO<>(true, null);
        }
    }

    @Override
    public MensajeDTO<UserSimplifyResponse> getUserBasicInfo(Long id) throws DomainException {
        try {
            Optional<User> optionalUser = userPort.findUserById(id);
            if (optionalUser.isEmpty()) {
                return new MensajeDTO<>(true, null);
            }

            User user = optionalUser.get();
            UserSimplifyResponse response = new UserSimplifyResponse(
                    user.getTypeDocument(),
                    user.getDocumentNumber(),
                    user.getFirstName(),
                    user.getSecondName(),
                    user.getLastName(),
                    user.getSecondLastName()
            );

            return new MensajeDTO<>(false, response);
        } catch (Exception e) {
            return new MensajeDTO<>(true, null);
        }
    }

    @Override
    public MensajeDTO<PageResponse<UserResponse>> getPagedUsers(int page, int size, String sort, String direction, String search) {
        Page<User> usersPage = userPort.findUsersWithPaginationAndSorting(page, size, sort, direction, search);

        List<UserResponse> items = usersPage.getContent().stream()
                .map(supply -> {
                    try {
                        return userDtoMapper.toResponse(supply);
                    } catch (DomainException e) {
                        throw new RuntimeException("Error al mapear usuario a DTO", e);
                    }
                })
                .collect(Collectors.toList());

        PageResponse<UserResponse> pageResponse = new PageResponse<>(
                items,
                usersPage.getNumber(),
                usersPage.getSize(),
                usersPage.getTotalElements(),
                usersPage.getTotalPages(),
                usersPage.isLast()
        );

        return new MensajeDTO<>(false, pageResponse);
    }
}