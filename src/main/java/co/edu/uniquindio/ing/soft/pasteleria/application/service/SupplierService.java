package co.edu.uniquindio.ing.soft.pasteleria.application.service;

import co.edu.uniquindio.ing.soft.pasteleria.application.dto.request.CreateSupplierCommand;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.request.UpdateSupplierCommand;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.response.SupplierResponse;
import co.edu.uniquindio.ing.soft.pasteleria.application.ports.input.ManageSupplierUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SupplierService implements ManageSupplierUseCase {
    @Override
    public SupplierResponse createSupplier(CreateSupplierCommand command) {
        return null;
    }

    @Override
    public SupplierResponse updateSupplier(Long id, UpdateSupplierCommand command) {
        return null;
    }

    @Override
    public void deleteSupplier(Long id) {

    }

    @Override
    public SupplierResponse getSupplier(Long id) {
        return null;
    }

    @Override
    public List<SupplierResponse> searchSupplier() {
        return List.of();
    }
}
