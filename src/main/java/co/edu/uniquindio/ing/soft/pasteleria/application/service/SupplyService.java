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
import co.edu.uniquindio.ing.soft.pasteleria.domain.model.Supply;
import co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.repository.SupplierJpaRepository;
import co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.repository.SupplyJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SupplyService implements ManageSupplyUseCase {

    private final SupplyPort supplyPort;
    private final SupplyDtoMapper supplyDtoMapper;
    private final SupplyJpaRepository supplyJpaRepository;
    private final SupplierPort supplierPort;

    @Override
    public SupplyResponse createSupply(CreateSupplyCommand command) throws DomainException {
        Optional<SupplyEntity> supplyOptional = supplyJpaRepository.findByName(command.name());
        if(supplyOptional.isPresent()) {
            throw new DomainException("Ya existe un insumo con el mismo nombre.");
        }

        // Validar que el proveedor existe
        if (!supplierPort.existsSupplierBySupplierID(command.supplierDocument())) {
            throw new DomainException("El proveedor con ID " + command.supplierDocument() + " no existe.");
        }

        Supply supply = new Supply(
                null,
                command.name(),
                command.price(),
                command.supplierDocument(),
                command.entryDate(),
                command.expirationDate(),
                command.quantity(),
                command.createdAt(),
                command.updatedAt()
        );

        try {
            Supply savedSupply = supplyPort.saveSupply(supply);
            return supplyDtoMapper.toResponse(savedSupply);
        } catch (DomainException e) {
            throw new RuntimeException("Error al crear el insumo: " + e.getMessage(), e);
        }
    }

    @Override
    public SupplyResponse updateSupply(Long id, UpdateSupplyCommand command) {
        try {
            Optional<Supply> optionalSupply = supplyPort.findSupplyById(id);
            if (optionalSupply.isEmpty()) {
                throw new RuntimeException("El insumo con id " + id + " no existe.");
            }

            Supply existingSupply = optionalSupply.get();
            existingSupply.setName(command.name());
            existingSupply.setPrice(command.price());
            existingSupply.setQuantity(command.quantity());
            existingSupply.setEntryDate(command.entryDate());
            existingSupply.setExpirationDate(command.expirationDate());
            existingSupply.setUpdatedAt(command.updatedAt());

            Supply updatedSupply = supplyPort.saveSupply(existingSupply);
            return supplyDtoMapper.toResponse(updatedSupply);

        } catch (DomainException e) {
            throw new RuntimeException("Error al actualizar el insumo: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteSupply(Long id) {
        supplyPort.deleteSupplyById(id);
    }

    @Override
    public SupplyResponse getSupply(Long id) throws DomainException {
        Optional<Supply> optionalSupply = supplyPort.findSupplyById(id);
        if (optionalSupply.isEmpty()) {
            return null;
        }
        return supplyDtoMapper.toResponse(optionalSupply.get());
    }

    @SneakyThrows
    @Override
    public List<SupplyResponse> searchSupply() {
        List<Supply> supplies = supplyPort.findAllSupplies();
        return supplies.stream().map(supply -> {
            try {
                return supplyDtoMapper.toResponse(supply);
            } catch (DomainException e) {
                throw new RuntimeException("Error al mapear Supply a SupplyResponse", e);
            }
        }).toList();
    }
}
