package co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.repository;

import co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.entity.SupplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplyJpaRepository extends JpaRepository<SupplyEntity, Long> {
    Optional<SupplyEntity> findByName(String name);

}
