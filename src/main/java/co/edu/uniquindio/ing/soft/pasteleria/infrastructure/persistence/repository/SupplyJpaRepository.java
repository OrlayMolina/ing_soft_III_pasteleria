package co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.repository;

import co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.entity.SupplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplyJpaRepository extends JpaRepository<SupplyEntity, Long> {
}
