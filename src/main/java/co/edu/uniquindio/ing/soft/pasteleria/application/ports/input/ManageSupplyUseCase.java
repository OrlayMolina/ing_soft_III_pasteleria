package co.edu.uniquindio.ing.soft.pasteleria.application.ports.input;

import co.edu.uniquindio.ing.soft.pasteleria.application.dto.request.CreateSupplyCommand;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.request.UpdateSupplyCommand;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.response.SupplyResponse;
import co.edu.uniquindio.ing.soft.pasteleria.domain.exception.DomainException;

import java.util.List;

public interface ManageSupplyUseCase {
    SupplyResponse createSupply(CreateSupplyCommand command) throws DomainException;
    SupplyResponse updateSupply(Long id, UpdateSupplyCommand command);
    void deleteSupply(Long id);
    SupplyResponse getSupply(Long id) throws DomainException;
    List<SupplyResponse> searchSupply();
}
