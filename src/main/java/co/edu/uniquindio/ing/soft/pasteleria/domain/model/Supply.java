package co.edu.uniquindio.ing.soft.pasteleria.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class Supply {
    private Long id;
    private String name;
    private String supplierDocument;
    private Double price;
    private LocalDate entryDate;
    private LocalDate expirationDate;
    private int quantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long userModify;

    public Supply(String name, String supplierDocument, Double price, LocalDate entryDate,
                  LocalDate expirationDate, int quantity, LocalDateTime createdAt,
                  LocalDateTime updatedAt, Long userModify) {
        this.name = name;
        this.supplierDocument = supplierDocument;
        this.price = price;
        this.entryDate = entryDate;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userModify = userModify;
    }
}
