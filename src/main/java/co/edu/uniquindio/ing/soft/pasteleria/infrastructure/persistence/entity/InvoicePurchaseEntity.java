package co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "invoice_purchases")
@Getter
@Setter
@NoArgsConstructor
public class InvoicePurchaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Fecha de la compra o emisión de la factura
    @Column(name = "purchase_date", nullable = false)
    private LocalDateTime purchaseDate;

    // IVA (impuesto al valor agregado)
    @NotNull
    @Column(name = "iva", nullable = false)
    private BigDecimal iva;

    // Total de la factura (valor de los productos + IVA, por ejemplo)
    @NotNull
    @Column(name = "total", nullable = false)
    private BigDecimal total;

    // Relación: cada factura se asocia a un único proveedor
    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private SupplierEntity supplier;

    // Relación con los insumos incluidos en la factura, a través de una entidad intermedia
    @OneToMany(mappedBy = "invoicePurchase", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoicePurchaseDetailEntity> purchaseDetails;
}
