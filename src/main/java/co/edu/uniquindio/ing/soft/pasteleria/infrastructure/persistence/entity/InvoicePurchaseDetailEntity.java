package co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "invoice_purchase_details")
@Getter
@Setter
@NoArgsConstructor
public class InvoicePurchaseDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con la factura
    @ManyToOne
    @JoinColumn(name = "invoice_purchase_id", nullable = false)
    private InvoicePurchaseEntity invoicePurchase;

    // Relación con el insumo
    @ManyToOne
    @JoinColumn(name = "supply_id", nullable = false)
    private SupplyEntity supply;

    // Cantidad de este insumo en la factura
    @NotNull
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}
