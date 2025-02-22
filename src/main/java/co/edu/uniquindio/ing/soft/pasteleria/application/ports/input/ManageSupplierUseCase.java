package co.edu.uniquindio.ing.soft.pasteleria.application.ports.input;

import co.edu.uniquindio.ing.soft.pasteleria.application.dto.request.CreateSupplierCommand;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.request.UpdateSupplierCommand;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.response.SupplierResponse;

import java.util.List;

public interface ManageSupplierUseCase {
    SupplierResponse createSupplier(CreateSupplierCommand command);
    SupplierResponse updateSupplier(Long id, UpdateSupplierCommand command);
    void deleteSupplier(Long id);
    SupplierResponse getSupplier(Long id);
    List<SupplierResponse> searchSupplier();
}
