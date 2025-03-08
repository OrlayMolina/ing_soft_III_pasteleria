package co.edu.uniquindio.ing.soft.pasteleria.application.ports.output;

import co.edu.uniquindio.ing.soft.pasteleria.domain.exception.DomainException;
import co.edu.uniquindio.ing.soft.pasteleria.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserPort {
    User saveUser(User user) throws DomainException;
    Optional<User> findUserById(Long id);
    void deleteUserById(Long id);
    List<User> findAllUsers();
    boolean existsUserById(Long id);
}
