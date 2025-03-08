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

    public Supply(Long id, String name, String supplierDocument, Double price, LocalDate entryDate,
                  LocalDate expirationDate, int quantity, LocalDateTime createdAt,
                  LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.supplierDocument = supplierDocument;
        this.price = price;
        this.entryDate = entryDate;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Supply(Long id, String name, String supplierDocument, Supplier supplier, Double price,
                  LocalDate entryDate, LocalDate expirationDate, int quantity,
                  LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.supplierDocument = supplierDocument;
        this.price = price;
        this.entryDate = entryDate;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Supply(Long id, String name, Supplier supplier, Double price, LocalDate entryDate,
                  LocalDate expirationDate, int quantity, LocalDateTime createdAt,
                  LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.supplierDocument = supplier.getSupplierDocument();
        this.price = price;
        this.entryDate = entryDate;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
