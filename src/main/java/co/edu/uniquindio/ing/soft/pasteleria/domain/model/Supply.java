package co.edu.uniquindio.ing.soft.pasteleria.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class Supply {
    private Long id;
    private String name;
    private String supplierID;
    private Supplier supplier;
    private Double price;
    private LocalDate entryDate;
    private LocalDate expirationDate;
    private int quantity;

    public Supply(Long id, String name, String supplierID, Double price, LocalDate entryDate,
                  LocalDate expirationDate, int quantity) {
        this.id = id;
        this.name = name;
        this.supplierID = supplierID;
        this.price = price;
        this.entryDate = entryDate;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
    }

    public Supply(Long id, String name, Supplier supplier, Double price, LocalDate entryDate,
                  LocalDate expirationDate, int quantity) {
        this.id = id;
        this.name = name;
        this.supplier = supplier;
        this.supplierID = supplier.getSupplierID();
        this.price = price;
        this.entryDate = entryDate;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
    }
}
