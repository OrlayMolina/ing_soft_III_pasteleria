package co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.adapter;

import co.edu.uniquindio.ing.soft.pasteleria.application.ports.output.SupplyPort;
import co.edu.uniquindio.ing.soft.pasteleria.domain.exception.DomainException;
import co.edu.uniquindio.ing.soft.pasteleria.domain.model.Supply;
import co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.entity.SupplyEntity;
import co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.mapper.SupplyPersistenceMapper;
import co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.repository.SupplyJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class SupplyPersistenceAdapter implements SupplyPort {
    private final SupplyJpaRepository supplyJpaRepository;
    private final SupplyPersistenceMapper persistenceMapper;

    @Override
    public Supply saveSupply(Supply supply) throws DomainException {
        SupplyEntity entityToSave = persistenceMapper.toEntity(supply);
        SupplyEntity savedEntity = supplyJpaRepository.save(entityToSave);
        return persistenceMapper.toDomain(savedEntity);
    }

    @Override
    public Supply updateSupply(Supply supply) throws DomainException {
        SupplyEntity existingEntity = supplyJpaRepository.findById(supply.getId())
                .orElseThrow(() -> new DomainException("Insumo no encontrado"));

        existingEntity.setName(supply.getName());
        existingEntity.setPrice(supply.getPrice());
        existingEntity.setEntryDate(supply.getEntryDate());
        existingEntity.setExpirationDate(supply.getExpirationDate());
        existingEntity.setQuantity(supply.getQuantity());
        existingEntity.setUpdatedAt(supply.getUpdatedAt());

        SupplyEntity updatedEntity = supplyJpaRepository.save(existingEntity);
        return persistenceMapper.toDomain(updatedEntity);
    }

    @Override
    public Optional<Supply> findSupplyById(Long id) {
        return supplyJpaRepository.findById(id)
                .map(entity -> {
                    try {
                        return persistenceMapper.toDomain(entity);
                    } catch (DomainException e) {
                        return null;
                    }
                });
    }

    @Override
    public void deleteSupplyById(Long id) {
        supplyJpaRepository.deleteById(id);
    }

    @Override
    public List<Supply> findAllSupplies() {
        return supplyJpaRepository.findAll().stream()
                .map(entity -> {
                    try {
                        return persistenceMapper.toDomain(entity);
                    } catch (DomainException e) {
                        return null;
                    }
                })
                .filter(supply -> supply != null)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsSupplyById(Long id) {
        return supplyJpaRepository.existsById(id);
    }

    @Override
    public Optional<SupplyEntity> existsSupplyBySupplierDocument(String supplierDocument) {
        return supplyJpaRepository.findBySupplierDocument(supplierDocument);
    }
}
