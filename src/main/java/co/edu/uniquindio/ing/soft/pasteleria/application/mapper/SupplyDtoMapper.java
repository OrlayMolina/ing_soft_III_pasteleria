package co.edu.uniquindio.ing.soft.pasteleria.application.mapper;

import co.edu.uniquindio.ing.soft.pasteleria.application.dto.request.CreateSupplyCommand;
import co.edu.uniquindio.ing.soft.pasteleria.application.dto.response.SupplyResponse;
import co.edu.uniquindio.ing.soft.pasteleria.domain.exception.DomainException;
import co.edu.uniquindio.ing.soft.pasteleria.domain.model.Supply;
import org.springframework.stereotype.Component;

@Component
public class SupplyDtoMapper {
    public Supply toModel(CreateSupplyCommand command, Long supplierId) throws DomainException {
        return new Supply(
                command.name(),
                command.supplierID(),
                supplierId,
                command.price(),
                command.entryDate(),
                command.expirationDate(),
                command.quantity(),
                command.createdAt(),
                command.updatedAt(),
                command.userModify()
        );
    }

    public SupplyResponse toResponse(Supply supply) throws DomainException {
        return new SupplyResponse(
                supply.getId(),
                supply.getName(),
                supply.getPrice(),
                supply.getEntryDate(),
                supply.getExpirationDate(),
                supply.getQuantity(),
                supply.getCreatedAt(),
                supply.getUpdatedAt()
        );
    }
}
