package co.edu.uniquindio.ing.soft.pasteleria.application.ports.input;

import co.edu.uniquindio.ing.soft.pasteleria.application.dto.request.CreateSupplierCommand;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.request.UpdateSupplierCommand;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.response.SupplierResponse;
import co.edu.uniquindio.ing.soft.pasteleria.domain.exception.DomainException;

import java.util.List;

public interface ManageSupplierUseCase {
    SupplierResponse createSupplier(CreateSupplierCommand command) throws DomainException;
    SupplierResponse updateSupplier(Long id, UpdateSupplierCommand command);
    void deleteSupplier(Long id);
    SupplierResponse getSupplier(Long id) throws DomainException;
    List<SupplierResponse> searchSupplier();
}
