package co.edu.uniquindio.ing.soft.pasteleria.application.dto.request;

import co.edu.uniquindio.ing.soft.pasteleria.domain.enums.Status;

import java.time.LocalDateTime;

public record UpdateSupplierCommand(
        Long id,
        String name,
        String supplierID,
        String address,
        String phone,
        String email,
        Status status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
