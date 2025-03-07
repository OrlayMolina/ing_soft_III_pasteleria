package co.edu.uniquindio.ing.soft.pasteleria.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class Supply {
    private String id;
    private String name;
    private String supplierID;
    private LocalDate entryDate;
    private LocalDate expirationDate;
    private int quantity;

    public Supply(String id, String name, String supplierID, LocalDate entryDate,
                  LocalDate expirationDate, int quantity) {
        this.id = id;
        this.name = name;
        this.supplierID = supplierID;
        this.entryDate = entryDate;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
    }
}
