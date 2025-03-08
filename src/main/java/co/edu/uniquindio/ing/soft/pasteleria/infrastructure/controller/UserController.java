package co.edu.uniquindio.ing.soft.pasteleria.infrastructure.controller;

import co.edu.uniquindio.ing.soft.pasteleria.application.dto.request.CreateUserCommand;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.request.UpdateUserCommand;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.response.UserResponse;
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
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserCommand command) {
        try {
            UserResponse response = manageUserUseCase.createUser(command);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (DomainException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserCommand command) {
        try {
            UserResponse response = manageUserUseCase.updateUser(id, command);
            if (response == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            manageUserUseCase.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        try {
            UserResponse response = manageUserUseCase.getUser(id);
            if (response == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DomainException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        try {
            List<UserResponse> users = manageUserUseCase.searchUser();
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
