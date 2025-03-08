package co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.adapter;

import co.edu.uniquindio.ing.soft.pasteleria.application.ports.output.SupplyPort;
import co.edu.uniquindio.ing.soft.pasteleria.domain.exception.DomainException;
import co.edu.uniquindio.ing.soft.pasteleria.domain.model.Supply;
import co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.mapper.SupplyPersistenceMapper;
import co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.repository.SupplyJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class SupplyPersistenceAdapter implements SupplyPort {
    private final SupplyJpaRepository supplyJpaRepository;
    private final SupplyPersistenceMapper persistenceMapper;

    @Override
    public Supply saveSupply(Supply supply) throws DomainException {
        return null;
    }

    @Override
    public Optional<Supply> findSupplyById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteSupplyById(Long id) {

    }

    @Override
    public List<Supply> findAllSupply() {
        return List.of();
    }

    @Override
    public boolean existsSupplyById(Long id) {
        return false;
    }
}
