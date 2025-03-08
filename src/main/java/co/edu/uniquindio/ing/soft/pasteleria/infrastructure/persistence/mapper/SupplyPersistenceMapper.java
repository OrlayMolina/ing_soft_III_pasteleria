package co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.mapper;

import co.edu.uniquindio.ing.soft.pasteleria.domain.exception.DomainException;
import co.edu.uniquindio.ing.soft.pasteleria.domain.model.Supply;
import co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.entity.SupplyEntity;
import org.springframework.stereotype.Component;

@Component
public class SupplyPersistenceMapper {

    public SupplyEntity toEntity(Supply supply) throws DomainException {
        SupplyEntity entity = new SupplyEntity();
        entity.setId(supply.getId());
        entity.setName(supply.getName());
        entity.setPrice(supply.getPrice());
        entity.setSupplierDocument(supply.getSupplierDocument());
        entity.setEntryDate(supply.getEntryDate());
        entity.setExpirationDate(supply.getExpirationDate());
        entity.setQuantity(supply.getQuantity());
        entity.setCreatedAt(supply.getCreatedAt());
        entity.setUpdatedAt(supply.getUpdatedAt());
        return entity;
    }

    public Supply toDomain(SupplyEntity entity) throws DomainException {
        return new Supply(
                entity.getName(),
                entity.getSupplierDocument(),
                entity.getPrice(),
                entity.getEntryDate(),
                entity.getExpirationDate(),
                entity.getQuantity(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getUserModify().getId()
        );
    }
}
