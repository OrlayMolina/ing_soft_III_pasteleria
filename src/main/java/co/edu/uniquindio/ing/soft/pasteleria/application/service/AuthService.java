package co.edu.uniquindio.ing.soft.pasteleria.application.service;

import co.edu.uniquindio.ing.soft.pasteleria.application.dto.LoginDTO;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.TokenDTO;
import co.edu.uniquindio.ing.soft.pasteleria.application.ports.input.ManageAuthUseCase;
import co.edu.uniquindio.ing.soft.pasteleria.domain.exception.DomainException;
import co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.adapter.config.JWTUtils;
import co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.entity.UserEntity;
import co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService implements ManageAuthUseCase {

    private final JWTUtils jwtUtils;
    private final UserJpaRepository userJpaRepository;

    @Override
    public TokenDTO logIn(LoginDTO loginDTO) throws DomainException {
        Optional<UserEntity> userEntity = userJpaRepository.findByEmail(loginDTO.email());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (userEntity.isEmpty()) {
            throw new DomainException("Usuario no encontrado");
        }

        if (!passwordEncoder.matches(loginDTO.password(), userEntity.get().getPassword())) {
            throw new DomainException("La contraseña es incorrecta");
        }
        Map<String, Object> map = construirClaims(userEntity.get());
        return new TokenDTO(jwtUtils.generarToken(userEntity.get().getEmail(), map));
    }

    private Map<String, Object> construirClaims(UserEntity userEntity) {
        return Map.of(
                "user_id", userEntity.getId(),
                "email", userEntity.getEmail(),
                "isAdmin", userEntity.getIsAdmin()
        );
    }
}
