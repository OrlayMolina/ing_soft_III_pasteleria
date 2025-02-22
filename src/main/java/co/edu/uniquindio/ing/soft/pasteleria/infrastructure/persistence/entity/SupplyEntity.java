package co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    // Relación: cada insumo es suministrado por un único proveedor
    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private SupplierEntity supplier;
}
