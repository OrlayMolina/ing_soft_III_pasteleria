package co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.adapter;

import co.edu.uniquindio.ing.soft.pasteleria.application.ports.output.UserPort;
import co.edu.uniquindio.ing.soft.pasteleria.domain.exception.DomainException;
import co.edu.uniquindio.ing.soft.pasteleria.domain.model.User;
import co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.entity.UserEntity;
import co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.mapper.UserPersistenceMapper;
import co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.repository.UserJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserPersistenceAdapter implements UserPort {

    private final UserJpaRepository userJpaRepository;
    private final UserPersistenceMapper persistenceMapper;

    @Override
    public User saveUser(User user) throws DomainException {
        UserEntity userEntity = persistenceMapper.toEntity(user);
        UserEntity savedEntity = userJpaRepository.save(userEntity);
        return persistenceMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        Optional<UserEntity> optionalEntity = userJpaRepository.findById(id);
        if (optionalEntity.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(persistenceMapper.toDomain(optionalEntity.get()));
        } catch (DomainException e) {
            throw new RuntimeException("Error al convertir UserEntity a dominio User", e);
        }
    }

    @Override
    public void deleteUserById(Long id) {
        userJpaRepository.deleteById(id);
    }

    @Override
    public List<User> findAllUsers() {
        List<UserEntity> entities = userJpaRepository.findAll();
        return entities.stream()
                .map(entity -> {
                    try {
                        return persistenceMapper.toDomain(entity);
                    } catch (DomainException e) {
                        throw new RuntimeException("Error al convertir lista de UserEntity a dominio User", e);
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsUserById(Long id) {
        return userJpaRepository.existsById(id);
    }
}
