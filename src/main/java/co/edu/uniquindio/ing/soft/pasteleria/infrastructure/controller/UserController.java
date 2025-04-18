package co.edu.uniquindio.ing.soft.pasteleria.infrastructure.controller;

import co.edu.uniquindio.ing.soft.pasteleria.application.dto.MensajeDTO;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.request.CreateUserCommand;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.request.UpdateUserCommand;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.response.UserResponse;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.response.UserSimplifyResponse;
import co.edu.uniquindio.ing.soft.pasteleria.application.ports.input.ManageUserUseCase;
import co.edu.uniquindio.ing.soft.pasteleria.domain.exception.DomainException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final ManageUserUseCase manageUserUseCase;

    @PostMapping
    public ResponseEntity<MensajeDTO<UserResponse>> createUser(@Valid @RequestBody CreateUserCommand command) {
        try {
            MensajeDTO<UserResponse> response = manageUserUseCase.createUser(command);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (DomainException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajeDTO<>(true, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MensajeDTO<>(true, null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensajeDTO<UserResponse>> updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserCommand command) {
        try {
            MensajeDTO<UserResponse> response = manageUserUseCase.updateUser(id, command);
            if (response.error() || response.respuesta() == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MensajeDTO<>(true, null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensajeDTO<Void>> deleteUser(@PathVariable Long id) {
        try {
            MensajeDTO<Void> response = manageUserUseCase.deleteUser(id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MensajeDTO<>(true, null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MensajeDTO<UserResponse>> getUser(@PathVariable Long id) {
        try {
            MensajeDTO<UserResponse> response = manageUserUseCase.getUser(id);
            if (response.error() || response.respuesta() == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            return ResponseEntity.ok(response);
        } catch (DomainException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajeDTO<>(true, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MensajeDTO<>(true, null));
        }
    }

    @GetMapping
    public ResponseEntity<MensajeDTO<List<UserResponse>>> getAllUsers() {
        try {
            MensajeDTO<List<UserResponse>> response = manageUserUseCase.searchUser();
            if (response.error() || response.respuesta() == null || response.respuesta().isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MensajeDTO<>(true, null));
        }
    }

    @GetMapping("/{id}/basic-info")
    public ResponseEntity<MensajeDTO<UserSimplifyResponse>> getUserBasicInfo(@PathVariable Long id) {
        try {
            MensajeDTO<UserSimplifyResponse> response = manageUserUseCase.getUserBasicInfo(id);
            if (response.error() || response.respuesta() == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            return ResponseEntity.ok(response);
        } catch (DomainException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajeDTO<>(true, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MensajeDTO<>(true, null));
        }
    }
}