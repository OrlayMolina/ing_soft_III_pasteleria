package co.edu.uniquindio.ing.soft.pasteleria;

import co.edu.uniquindio.ing.soft.pasteleria.domain.exception.DomainException;
import co.edu.uniquindio.ing.soft.pasteleria.domain.model.Supplier;
import co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.entity.SupplierEntity;
import co.edu.uniquindio.ing.soft.pasteleria.infrastructure.persistence.repository.SupplierJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static co.edu.uniquindio.ing.soft.pasteleria.utils.RandomUtil.getRandomElement;

@SpringBootTest
public class SupplyServiceTests {

    @Autowired
    private SupplierJpaRepository jpaRepository;


    @Test
    public void createSupply() throws DomainException {
        List<SupplierEntity> suppliers = jpaRepository.findAll();
        SupplierEntity supplier = getRandomElement(suppliers);

    }
}
