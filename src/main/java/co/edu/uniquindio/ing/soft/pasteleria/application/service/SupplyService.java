package co.edu.uniquindio.ing.soft.pasteleria.application.service;

import co.edu.uniquindio.ing.soft.pasteleria.application.dto.request.CreateSupplyCommand;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.request.UpdateSupplyCommand;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.response.SupplyResponse;
import co.edu.uniquindio.ing.soft.pasteleria.application.mapper.SupplierDtoMapper;
import co.edu.uniquindio.ing.soft.pasteleria.application.mapper.SupplyDtoMapper;
import co.edu.uniquindio.ing.soft.pasteleria.application.ports.input.ManageSupplyUseCase;
import co.edu.uniquindio.ing.soft.pasteleria.application.ports.output.SupplierPort;
import co.edu.uniquindio.ing.soft.pasteleria.application.ports.output.SupplyPort;
import co.edu.uniquindio.ing.soft.pasteleria.domain.exception.DomainException;
import co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.repository.SupplierJpaRepository;
import co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.repository.SupplyJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SupplyService implements ManageSupplyUseCase {

    private final SupplyPort supplyPort;
    private final SupplyDtoMapper supplyDtoMapper;
    private final SupplyJpaRepository supplyJpaRepository;

    @Override
    public SupplyResponse createSupply(CreateSupplyCommand command) throws DomainException {
        return null;
    }

    @Override
    public SupplyResponse updateSupply(Long id, UpdateSupplyCommand command) {
        return null;
    }

    @Override
    public void deleteSupply(Long id) {

    }

    @Override
    public SupplyResponse getSupply(Long id) throws DomainException {
        return null;
    }

    @Override
    public List<SupplyResponse> searchSupply() {
        return List.of();
    }
}
