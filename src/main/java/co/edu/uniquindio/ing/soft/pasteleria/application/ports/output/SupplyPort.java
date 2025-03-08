package co.edu.uniquindio.ing.soft.pasteleria.application.ports.output;

import co.edu.uniquindio.ing.soft.pasteleria.domain.exception.DomainException;
import co.edu.uniquindio.ing.soft.pasteleria.domain.model.Supplier;
import co.edu.uniquindio.ing.soft.pasteleria.domain.model.Supply;

import java.util.List;
import java.util.Optional;

public interface SupplyPort {
    Supply saveSupply(Supply supply) throws DomainException;
    Optional<Supply> findSupplyById(Long id);
    void deleteSupplyById(Long id);
    List<Supply> findAllSupply();
    boolean existsSupplyById(Long id);
}
