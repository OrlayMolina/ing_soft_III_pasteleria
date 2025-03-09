package co.edu.uniquindio.ing.soft.pasteleria.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UpdateSupplyCommand(
        Long id,
        @NotBlank(message = "El nombre del insumo requerido")
        String name,
        @Positive
        Double price,
        @NotNull(message = "La fecha de entrada del insumo es requerido")
        LocalDate entryDate,
        @NotNull(message = "La fecha de salida del insumo es requerido")
        LocalDate expirationDate,
        @Positive
        int quantity,
        @NotNull(message = "Fecha de actualización de un insumo es requerida")
        LocalDateTime updatedAt) {
}
