package co.edu.uniquindio.ing.soft.pasteleria.application.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CreateSupplyCommand(
        Long id,
        @NotBlank(message = "El nombre del insumo requerido")
        String name,
        Double price,
        @NotNull(message = "La fecha de entrada del insumo es requerido")
        LocalDate entryDate,
        LocalDate expirationDate,
        int quantity,
        @NotNull(message = "Created date is required")
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
