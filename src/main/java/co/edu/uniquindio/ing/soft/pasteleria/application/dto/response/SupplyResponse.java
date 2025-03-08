package co.edu.uniquindio.ing.soft.pasteleria.application.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record SupplyResponse(
        Long id,
        String name,
        Double price,
        LocalDate entryDate,
        LocalDate expirationDate,
        int quantity,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
