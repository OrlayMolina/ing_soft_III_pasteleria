package co.edu.uniquindio.ing.soft.pasteleria.application.mapper;

import co.edu.uniquindio.ing.soft.pasteleria.application.dto.request.CreateSupplierCommand;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.response.SupplierResponse;
import co.edu.uniquindio.ing.soft.pasteleria.domain.enums.Status;
import co.edu.uniquindio.ing.soft.pasteleria.domain.exception.DomainException;
import co.edu.uniquindio.ing.soft.pasteleria.domain.model.Supplier;

import org.springframework.stereotype.Component;

@Component
public class SupplierDtoMapper {
    public Supplier toModel(CreateSupplierCommand command) throws DomainException {
        return new Supplier(
                null,
                command.name(),
                command.supplierID(),
                command.address(),
                command.phone(),
                command.email(),
                Status.ACTIVO,
                command.createdAT(),
                command.updatedAt()
        );
    }

    public SupplierResponse toResponse(Supplier supplier) throws DomainException {
        return new SupplierResponse(
                supplier.getId(),
                supplier.getName(),
                supplier.getSupplierID(),
                supplier.getAddress(),
                supplier.getPhone(),
                supplier.getEmail(),
                supplier.getStatus(),
                supplier.getCreatedAt(),
                supplier.getUpdatedAt()
        );
    }
}