package co.edu.uniquindio.ing.soft.pasteleria.application.ports.output;

import co.edu.uniquindio.ing.soft.pasteleria.domain.exception.DomainException;
import co.edu.uniquindio.ing.soft.pasteleria.domain.model.Supply;
import co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.entity.SupplyEntity;

import java.util.List;
import java.util.Optional;

public interface SupplyPort {
    Supply saveSupply(Supply supply) throws DomainException;
    Supply updateSupply(Supply supply) throws DomainException;
    Optional<Supply> findSupplyById(Long id);
    void deleteSupplyById(Long id);
    List<Supply> findAllSupplies();
    boolean existsSupplyById(Long id);
    Optional<SupplyEntity> existsSupplyBySupplierDocument(String supplierDocument);

}
