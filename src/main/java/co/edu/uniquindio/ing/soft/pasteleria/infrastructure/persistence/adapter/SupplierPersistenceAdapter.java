package co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.adapter;

import co.edu.uniquindio.ing.soft.pasteleria.application.ports.output.SupplierPort;
import co.edu.uniquindio.ing.soft.pasteleria.domain.exception.DomainException;
import co.edu.uniquindio.ing.soft.pasteleria.domain.model.Supplier;
import co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.entity.SupplierEntity;
import co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.mapper.SupplierPersistenceMapper;
import co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.repository.SupplierJpaRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
@AllArgsConstructor
public class SupplierPersistenceAdapter implements SupplierPort {

    private final SupplierJpaRepository supplierJpaRepository;
    private final SupplierPersistenceMapper persistenceMapper;  // Mapper de persistencia

    @Override
    public Supplier saveSupplier(Supplier supplier) throws DomainException {
        SupplierEntity entity = persistenceMapper.toEntity(supplier);
        SupplierEntity savedEntity = supplierJpaRepository.save(entity);
        return persistenceMapper.toDomain(savedEntity);
    }

    @SneakyThrows
    @Override
    public Optional<Supplier> findSupplierById(Long id) {
        return supplierJpaRepository.findById(id)
                .map(entity -> {
                    try {
                        return persistenceMapper.toDomain(entity);
                    } catch (DomainException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Override
    public void deleteSupplierById(Long id) {
        supplierJpaRepository.deleteById(id);
    }

    @SneakyThrows
    @Override
    public List<Supplier> findAllSuppliers() {
        return supplierJpaRepository.findAll().stream()
                .map(entity -> {
                    try {
                        return persistenceMapper.toDomain(entity);
                    } catch (DomainException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsSupplierById(Long id) {
        return supplierJpaRepository.existsById(id);
    }
}
