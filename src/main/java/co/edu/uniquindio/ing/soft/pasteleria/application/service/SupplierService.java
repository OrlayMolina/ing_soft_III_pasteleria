package co.edu.uniquindio.ing.soft.pasteleria.application.service;

import co.edu.uniquindio.ing.soft.pasteleria.application.dto.request.CreateSupplierCommand;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.request.UpdateSupplierCommand;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.response.SupplierResponse;
import co.edu.uniquindio.ing.soft.pasteleria.application.mapper.SupplierDtoMapper;
import co.edu.uniquindio.ing.soft.pasteleria.application.ports.input.ManageSupplierUseCase;
import co.edu.uniquindio.ing.soft.pasteleria.application.ports.output.SupplierPort;
import co.edu.uniquindio.ing.soft.pasteleria.domain.exception.DomainException;
import co.edu.uniquindio.ing.soft.pasteleria.domain.model.Supplier;
import co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.entity.SupplierEntity;
import co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.repository.SupplierJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SupplierService implements ManageSupplierUseCase {

    private final SupplierPort supplierPort;
    private final SupplierDtoMapper supplierDtoMapper;
    private final SupplierJpaRepository supplierJpaRepository;

    @Override
    public SupplierResponse createSupplier(CreateSupplierCommand command) throws DomainException {

        Optional<SupplierEntity> supplierOptional = supplierJpaRepository.findBySupplierID(command.supplierID());
        if(supplierOptional.isPresent()){
            throw new DomainException("Ya existe un proveedor con el mismo número de ID.");
        }
        Supplier supplier = new Supplier(
                null,
                command.name(),
                command.supplierID(),
                command.address(),
                command.phone(),
                command.email(),
                command.status(),
                command.createdAt(),
                command.updatedAt()
        );

        try {
            Supplier savedSupplier = supplierPort.saveSupplier(supplier);

            return supplierDtoMapper.toResponse(savedSupplier);

        } catch (DomainException e) {
            throw new RuntimeException("Error al crear el proveedor: " + e.getMessage(), e);
        }
    }

    @Override
    public SupplierResponse updateSupplier(Long id, UpdateSupplierCommand command) {
        try {
            Optional<Supplier> optionalSupplier = supplierPort.findSupplierById(id);
            if (optionalSupplier.isEmpty()) {
                throw new RuntimeException("El proveedor con id " + id + " no existe.");
            }

            Supplier existingSupplier = optionalSupplier.get();
            existingSupplier.setName(command.name());
            existingSupplier.setAddress(command.address());
            existingSupplier.setPhone(command.phone());
            existingSupplier.setEmail(command.email());
            existingSupplier.setStatus(command.status());
            existingSupplier.setCreatedAt(command.createdAt());
            existingSupplier.setUpdatedAt(command.updatedAt());

            Supplier updatedSupplier = supplierPort.saveSupplier(existingSupplier);
            return supplierDtoMapper.toResponse(updatedSupplier);

        } catch (DomainException e) {
            throw new RuntimeException("Error al actualizar el proveedor: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteSupplier(Long id) {
        supplierPort.deleteSupplierById(id);
    }

    @Override
    public SupplierResponse getSupplier(Long id) throws DomainException {
        Optional<Supplier> optionalSupplier = supplierPort.findSupplierById(id);
        if (optionalSupplier.isEmpty()) {
            return null;
        }
        return supplierDtoMapper.toResponse(optionalSupplier.get());
    }

    @SneakyThrows
    @Override
    public List<SupplierResponse> searchSupplier() {
        List<Supplier> suppliers = supplierPort.findAllSuppliers();
        return suppliers.stream().map(supplier -> {
            try {
                return supplierDtoMapper.toResponse(supplier);
            } catch (DomainException e) {
                throw new RuntimeException("Error al mapear Supplier a SupplierResponse", e);
            }
        }).toList();
    }
}
