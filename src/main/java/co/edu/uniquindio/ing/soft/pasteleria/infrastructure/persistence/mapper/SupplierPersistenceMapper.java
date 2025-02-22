package co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.mapper;

import co.edu.uniquindio.ing.soft.pasteleria.domain.exception.DomainException;
import co.edu.uniquindio.ing.soft.pasteleria.domain.model.Supplier;
import co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.entity.SupplierEntity;
import org.springframework.stereotype.Component;

@Component
public class SupplierPersistenceMapper {
    public SupplierEntity toEntity(Supplier supplier) throws DomainException {
        SupplierEntity entity = new SupplierEntity();
        entity.setId(supplier.getId());
        entity.setName(supplier.getName());
        entity.setSupplierID(supplier.getSupplierID());
        entity.setAddress(supplier.getAddress());
        entity.setPhone(supplier.getPhone());
        entity.setEmail(supplier.getEmail());
        entity.setStatus(supplier.getStatus());
        entity.setCreatedAt(supplier.getCreatedAt());
        entity.setUpdatedAt(supplier.getUpdatedAt());
        return entity;
    }

    public Supplier toDomain(SupplierEntity entity) throws DomainException {
        return new Supplier(
                entity.getId(),
                entity.getName(),
                entity.getSupplierID(),
                entity.getAddress(),
                entity.getPhone(),
                entity.getEmail(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}