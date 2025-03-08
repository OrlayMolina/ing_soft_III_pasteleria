package co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "supplies")
@Getter
@Setter
@NoArgsConstructor
public class SupplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name", nullable = false)
    @Size(min = 3, max = 50)
    private String name;
    @Column(name = "price", nullable = false)
    private Double price;
    @NotBlank
    @Column(name = "supplier_document", nullable = false)
    private String supplierDocument;

    @Column(name = "entry_date")
    private LocalDate entryDate;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Relación: cada insumo es suministrado por un único proveedor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = false)
    private SupplierEntity supplier;
}