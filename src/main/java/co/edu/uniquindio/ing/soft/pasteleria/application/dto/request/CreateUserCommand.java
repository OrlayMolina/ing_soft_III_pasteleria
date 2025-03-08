package co.edu.uniquindio.ing.soft.pasteleria.application.dto.request;

import co.edu.uniquindio.ing.soft.pasteleria.domain.enums.Status;
import co.edu.uniquindio.ing.soft.pasteleria.domain.enums.TypeDocument;

import java.time.LocalDateTime;

public record CreateUserCommand(
        Long id,
        TypeDocument typeDocument,
        String phone,
        Long position,
        Float salary,
        String first_name,
        String second_name,
        String last_name,
        String second_last_name,
        String email,
        String password,
        Status status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
