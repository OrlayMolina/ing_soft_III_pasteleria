package co.edu.uniquindio.ing.soft.pasteleria.application.dto.response;

import co.edu.uniquindio.ing.soft.pasteleria.domain.enums.Status;

import java.time.LocalDateTime;

public record SupplierResponse(
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
