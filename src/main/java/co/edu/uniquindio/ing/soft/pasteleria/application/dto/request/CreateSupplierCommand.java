package co.edu.uniquindio.ing.soft.pasteleria.application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateSupplierCommand(
        @NotBlank(message = "El nombre es requerido")
        String name,

        @NotBlank(message = "El ID del proveedor es requerido")
        String supplierID,

        @NotBlank(message = "La dirección es requerida")
        String address,

        @NotBlank(message = "El telefono es requerido")
        String phone,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @NotNull(message = "Created date is required")
        LocalDateTime createdAT,

        @NotNull(message = "Updated date is required")
        LocalDateTime updatedAt) {
}
