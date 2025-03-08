package co.edu.uniquindio.ing.soft.pasteleria.application.dto.request;

import co.edu.uniquindio.ing.soft.pasteleria.domain.enums.Status;
import co.edu.uniquindio.ing.soft.pasteleria.domain.enums.TypeDocument;

import java.time.LocalDateTime;

public record UpdateUserCommand(
        Long id,
        TypeDocument typeDocument,
        String documentNumber,
        String phone,
        String position,
        Float salary,
        String firstName,
        String secondName,
        String lastName,
        String secondLastName,
        String email,
        String password,
        Status status,
        Boolean isAdmin,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
